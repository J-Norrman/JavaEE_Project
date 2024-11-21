function showSearchBar(type) {
    // Hide both search bars initially
    document.getElementById('currentWeatherSearch').classList.remove('active');
    document.getElementById('forecastSearch').classList.remove('active');

    // Show the selected search bar
    if (type === 'current') {
        document.getElementById('currentWeatherSearch').classList.add('active');
    } else if (type === 'forecast') {
        document.getElementById('forecastSearch').classList.add('active');
    }
}
