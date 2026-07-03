const button = document.getElementById('themeToggle');
const savedTheme = localStorage.getItem('tourism-theme');
if (savedTheme === 'dark') document.body.classList.add('dark-mode');
if (button) {
  button.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');
    localStorage.setItem('tourism-theme', document.body.classList.contains('dark-mode') ? 'dark' : 'light');
  });
}
