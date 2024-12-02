function showSearchBar(type) {
    document.getElementById('currentWeatherSearch').classList.remove('active');
    document.getElementById('forecastSearch').classList.remove('active');
    if (type === 'current') {
        document.getElementById('currentWeatherSearch').classList.add('active');
    } else if (type === 'forecast') {
        document.getElementById('forecastSearch').classList.add('active');
    }
}
