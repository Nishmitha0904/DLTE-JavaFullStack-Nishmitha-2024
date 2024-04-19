
function displayDepositCards() {
    // Retrieve deposit details from local storage
    var deposits = JSON.parse(localStorage.getItem("deposits"));

    // Display deposit cards
    var depositDetailsContainer = document.getElementById("depositDetails");
    depositDetailsContainer.innerHTML = ''; 
    deposits.forEach(function(deposit) {
        var cardHtml = `
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title text-center">${deposit.name}</h2>
                        <hr>
                        <div class="card-text text-center">
                            <p>ROI: ${deposit.roi}%</p>
                        </div>
                        <div id="${deposit.id}Details" class="collapse">
                            <hr>
                            <div class="card-text">
                                <p>Type: ${deposit.type}</p>
                                <p>${deposit.description}</p>
                            </div>
                        </div>
                        <div class="text-center">
                            <button class="btn btn-outline-primary" data-bs-toggle="collapse" data-bs-target="#${deposit.id}Details" aria-expanded="false" aria-controls="${deposit.id}Details" onclick="toggleButton(this)">Know More</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
        depositDetailsContainer.innerHTML += cardHtml;
    });
}

var deposits = [
    { id: 1, name: "Fixed Savings", roi: 4.5, type: "Term Deposit", description: "A fixed-term savings account" },
    { id: 2, name: "Flexi Saver", roi: 3.2, type: "Savings Account", description: "A flexible savings account" },
    { id: 3, name: "SteadySaver RD", roi: 5.0, type: "Recurring Deposit", description: "A disciplined savings account where you contribute a fixed amount regularly" }
];

localStorage.setItem("deposits", JSON.stringify(deposits));

function toggleButton(button) {
    if (button.innerHTML === 'Know More') {
        button.innerHTML = 'Show Less';
    } else {
        button.innerHTML = 'Know More';
    }
}
