const movies = {
    "Avengers": [false, false, false, false, false],
    "Inception": [false, false, false, false, false],
    "Spider-Man": [false, false, false, false, false]
};

let selectedMovie = "";
let selectedSeat = null;

function loadSeats() {
    selectedMovie = document.getElementById("movie-select").value;
    const seatContainer = document.getElementById("seats");
    seatContainer.innerHTML = "";
    selectedSeat = null;

    if (!selectedMovie) return;

    movies[selectedMovie].forEach((isBooked, index) => {
        const seat = document.createElement("button");
        seat.textContent = index + 1;
        seat.classList.add("seat");
        if (isBooked) {
            seat.classList.add("booked");
            seat.disabled = true;
        } else {
            seat.onclick = () => selectSeat(index, seat);
        }
        seatContainer.appendChild(seat);
    });
}

function selectSeat(index, seatElement) {
    document.querySelectorAll(".seat").forEach(seat => seat.classList.remove("selected"));
    seatElement.classList.add("selected");
    selectedSeat = index;
}

function bookSeat() {
    if (!selectedMovie || selectedSeat === null) {
        document.getElementById("message").textContent = "⚠️ Please select a movie and a seat.";
        return;
    }

    if (movies[selectedMovie][selectedSeat]) {
        document.getElementById("message").textContent = "❌ Seat already booked!";
        return;
    }

    movies[selectedMovie][selectedSeat] = true;
    document.getElementById("message").textContent = `✅ Booked seat ${selectedSeat + 1} for ${selectedMovie}!`;

    loadSeats();
}
