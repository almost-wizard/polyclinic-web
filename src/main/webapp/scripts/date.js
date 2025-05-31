const dateInput = document.getElementById('dateInput');

function getTodayYYYYMMDD() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // Month is 0-indexed
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function setInitialDate() {
    const urlParams = new URLSearchParams(window.location.search);
    const serverDateString = urlParams.get('date');

    if (serverDateString) {
        dateInput.value = serverDateString;
    } else {
        dateInput.value = getTodayYYYYMMDD();
    }
}

window.addEventListener('load', setInitialDate);
