module.exports = {
  content: [
    "./src/main/resources/templates/**/*.{html,js}",
    "./src/main/resources/static/**/*.{html,js}",
    "./node_modules/flowbite/**/*.js"   // important for Flowbite
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin')          // required for Flowbite components
  ],
  darkMode: 'class',  // we ignore dark mode for now
}
