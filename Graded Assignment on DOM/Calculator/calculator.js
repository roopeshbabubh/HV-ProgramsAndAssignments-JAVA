let currentInput = '';
let currentOperator = '';
let resultDisplay = document.getElementById('result');

function appendNumber(number) {
  currentInput += number;
  updateDisplay();
}

function add() {
  currentOperator = '+';
  currentInput += '+';
  updateDisplay();
}

function subtract() {
  currentOperator = '-';
  currentInput += '-';
  updateDisplay();
}

function multiply() {
  currentOperator = '*';
  currentInput += '*';
  updateDisplay();
}

function divide() {
  currentOperator = '/';
  currentInput += '/';
  updateDisplay();
}

function calculate() {
  try {
    const result = eval(currentInput);
    currentInput = result.toString();
    updateDisplay();
  } catch (error) {
    currentInput = 'Error';
    updateDisplay();
  }
}

function clearResult() {
  currentInput = '';
  currentOperator = '';
  updateDisplay();
}

function updateDisplay() {
  resultDisplay.value = currentInput;
}