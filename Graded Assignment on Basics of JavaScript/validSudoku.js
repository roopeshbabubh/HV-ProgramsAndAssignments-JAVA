function isValidSudoku(board) {
    // Check each row
    for (let i = 0; i < 9; i++) {
      if (!isValidUnit(board[i])) {
        return false;
      }
    }
  
    // Check each column
    for (let i = 0; i < 9; i++) {
      const column = [];
      for (let j = 0; j < 9; j++) {
        column.push(board[j][i]);
      }
      if (!isValidUnit(column)) {
        return false;
      }
    }
  
    // Check each 3x3 sub-box
    for (let i = 0; i < 9; i += 3) {
      for (let j = 0; j < 9; j += 3) {
        const subBox = [];
        for (let x = 0; x < 3; x++) {
          for (let y = 0; y < 3; y++) {
            subBox.push(board[i + x][j + y]);
          }
        }
        if (!isValidUnit(subBox)) {
          return false;
        }
      }
    }
  
    return true;
  }
  
  function isValidUnit(unit) {
    const seen = new Set();
    for (const num of unit) {
      if (num !== '.' && seen.has(num)) {
        return false;
      }
      seen.add(num);
    }
    return true;
  }
  
  const sudokuBoard = [
    ["5","3",".",".","7",".",".",".","."],
    ["6",".",".","1","9","5",".",".","."],
    [".","9","8",".",".",".",".","6","."],
    ["8",".",".",".","6",".",".",".","3"],
    ["4",".",".","8",".","3",".",".","1"],
    ["7",".",".",".","2",".",".",".","6"],
    [".","6",".",".",".",".","2","8","."],
    [".",".",".","4","1","9",".",".","5"],
    [".",".",".",".","8",".",".","7","9"]
  ];
  
  console.log(isValidSudoku(sudokuBoard));
  