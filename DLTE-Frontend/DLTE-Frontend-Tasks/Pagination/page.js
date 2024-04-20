var deposits = [
    { id: 1, name: "Fixed Savings", roi: 6.5, type: "Term Deposit", description: "A fixed-term savings account" },
    { id: 2, name: "Flexi Saver", roi: 5.2, type: "Savings Account", description: "A flexible savings account" },
    { id: 3, name: "SteadySaver RD", roi: 7.0, type: "Recurring Deposit", description: "A disciplined savings account where you contribute a fixed amount regularly" },
    { id: 4, name: "Maxi Deposit", roi: 11.5, type: "Fixed Deposit", description: "High-interest term deposit" },
    { id: 5, name: "Holiday Fund", roi: 3.2, type: "Savings Account", description: "Save for your dream vacation" },
    { id: 6, name: "Wealth Grower", roi: 5.0, type: "Investment Account", description: "Invest in your future" }
];
localStorage.setItem("deposits", JSON.stringify(deposits));


function displayDeposit() {
    // Retrieve deposit details from local storage
    var deposits = JSON.parse(localStorage.getItem("deposits"));
    
    let recordsPerPage = 2;
    let currentPage = 1;

    const viewRecords = () => {
        const begin = (currentPage - 1) * recordsPerPage;
        const end = begin + recordsPerPage;
        const size = deposits.length;

        let tableBody = $("#records");
        tableBody.empty(); // Clear previous content
        for (let index = begin; index < end && index < size; index++) {
            tableBody.append("<tr><td>" + deposits[index].id + "</td><td>" + deposits[index].name + "</td><td>" +
                deposits[index].roi + "</td><td>" + deposits[index].type + "</td><td>" +
                deposits[index].description + "</td></tr>");
        }
    }

    $("document").ready(() => {
        viewRecords();

        // Pagination
        const totalPages = Math.ceil(deposits.length / recordsPerPage);
        let pagination = $("#pagination");
        for (let i = 1; i <= totalPages; i++) {
            pagination.append(`<button class="btn btn-sm btn-secondary" onclick="currentPage=${i}; viewRecords();">${i}</button>`);
        }

        // Filter by ROI
        $("#roi").change(() => {
            const filteredDeposits = deposits.filter(deposit => deposit.roi == $("#roi").val());
            viewRecords(filteredDeposits);
        });
    });
}








