const API_URL = 'http://localhost:8080/api/suggestions';

// Load suggestions automatically when opening the page
window.onload = function() {
    loadSuggestions();
};

async function loadSuggestions() {
    const listContainer = document.getElementById('suggestionsList');
    listContainer.innerHTML = '<p>Loading suggestions...</p>';

    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Failed to fetch suggestions');
        
        const suggestions = await response.json();

        if (suggestions.length === 0) {
            listContainer.innerHTML = '<p>No suggestions available yet.</p>';
            return;
        }

        listContainer.innerHTML = '';
        suggestions.forEach(s => {
            const item = document.createElement('div');
            item.className = 'suggestion-item';
            item.innerHTML = `
                <h3>[ID: ${s.id}] ${escapeHtml(s.title)}</h3>
                <p><strong>Content:</strong> ${escapeHtml(s.content)}</p>
                <p><strong>Proposed Solution:</strong> ${escapeHtml(s.solution || 'N/A')}</p>
                <p><small>Created at: ${s.createdAt || 'N/A'}</small></p>
                
                <div class="rating-box">
                    <!-- Interactive Stars Component -->
                    <div class="star-rating" id="stars-${s.id}">
                        <span class="star active" data-value="1" onclick="setRating(${s.id}, 1)">★</span>
                        <span class="star active" data-value="2" onclick="setRating(${s.id}, 2)">★</span>
                        <span class="star active" data-value="3" onclick="setRating(${s.id}, 3)">★</span>
                        <span class="star active" data-value="4" onclick="setRating(${s.id}, 4)">★</span>
                        <span class="star active" data-value="5" onclick="setRating(${s.id}, 5)">★</span>
                    </div>
                    <!-- Hidden input to store selected value (default 5) -->
                    <input type="hidden" id="rating-val-${s.id}" value="5">

                    <input type="text" id="nick-${s.id}" placeholder="Your nick (optional)" style="width: 150px;">
                    <button onclick="submitRating(${s.id})">Rate</button>
                </div>
            `;
            listContainer.appendChild(item);
        });

    } catch (err) {
        listContainer.innerHTML = '<p style="color: #ffcdd2;">Server connection error (Is Spring Boot running?).</p>';
    }
}

// Handle star selection visual update
function setRating(suggestionId, value) {
    document.getElementById(`rating-val-${suggestionId}`).value = value;
    const stars = document.querySelectorAll(`#stars-${suggestionId} .star`);
    
    stars.forEach(star => {
        const starValue = parseInt(star.getAttribute('data-value'));
        if (starValue <= value) {
            star.classList.add('active');
        } else {
            star.classList.remove('active');
        }
    });
}

async function createSuggestion() {
    const title = document.getElementById('title').value;
    const content = document.getElementById('description').value; // Mapeado para 'content'
    const solution = document.getElementById('proposedSolution').value; // Mapeado para 'solution'

    if (!title || !content) {
        alert('Title and content are required.');
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title, content, solution, rating: 5 })
        });

        if (response.ok) {
            alert('Suggestion created successfully!');
            document.getElementById('title').value = '';
            document.getElementById('description').value = '';
            document.getElementById('proposedSolution').value = '';
            loadSuggestions(); // Reload list
        } else {
            alert('Failed to create suggestion.');
        }
    } catch (err) {
        alert('Server connection error.');
    }
}

async function submitRating(suggestionId) {
    const ratingValue = document.getElementById(`rating-val-${suggestionId}`).value;
    const nickname = document.getElementById(`nick-${suggestionId}`).value;

    let url = `${API_URL}/${suggestionId}/rate?ratingValue=${ratingValue}`;
    if (nickname) {
        url += `&nickname=${encodeURIComponent(nickname)}`;
    }

    try {
        const response = await fetch(url, { method: 'POST' });
        const data = await response.text();

        if (response.ok) {
            alert('Rating registered successfully!');
            loadSuggestions();
        } else if (response.status === 409) {
            alert('Error: You have already rated this suggestion with this device.');
        } else {
            alert('Error: ' + data);
        }
    } catch (err) {
        alert('Server connection error.');
    }
}

// Helper function to avoid basic XSS in preview
function escapeHtml(text) {
    if (!text) return '';
    return text.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
}