const n = 5; // number of lines
const m = 5; // number of columns
const c = 4; // number of colors

let count = 0;
const visited = Array(n)
  .fill(0)
  .map(() => new Array(m).fill(0));

const result = Array(n)
  .fill(0)
  .map(() => new Array(m).fill(0));

initTable = function (nbLines, nbColumns, nbColors) {
  let array = Array(nbLines)
    .fill(0)
    .map(() => new Array(nbColumns).fill(0));

  for (let i = 0; i < nbLines; i++) {
    {
      for (let j = 0; j < nbColumns; j++) {
        {
          array[i][j] = Math.floor(Math.random() * nbColors) | 0;
        }
      }
    }
  }
  return array;
};

resetVisited = function () {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      visited[i][j] = 0;
    }
  }
};

BreadthFirstSearch = function (x, y, i, j, input) {
  if (x !== y) return;
  visited[i][j] = 1;
  count++;
  let xMove = [0, 0, 1, -1];
  let yMove = [1, -1, 0, 0];
  for (let u = 0; u < 4; u++) {
    if (isValid(i + yMove[u], j + xMove[u], x, input) === true)
      BreadthFirstSearch(x, y, i + yMove[u], j + xMove[u], input);
  }
};

resetResult = function (key, input) {
  for (let i = 0; i < n; i++) {
    {
      for (let j = 0; j < m; j++) {
        {
          if (visited[i][j] === 1 && input[i][j] === key)
            result[i][j] = visited[i][j];
          else result[i][j] = 0;
        }
      }
    }
  }
};

isValid = function (x, y, key, input) {
  if (x < n && y < m && x >= 0 && y >= 0) {
    if (visited[x][y] === 0 && input[x][y] === key) return true;
    else return false;
  } else return false;
};

FindLargestArea = function (input) {
  let currentMax = -1;
  for (let i = 0; i < n; i++) {
    {
      for (let j = 0; j < m; j++) {
        {
          resetVisited();
          count = 0;
          if (j + 1 < m)
            BreadthFirstSearch(input[i][j], input[i][j + 1], i, j, input);
          if (count >= currentMax) {
            currentMax = count;
            resetResult(input[i][j], input);
          }
          resetVisited();
          count = 0;
          if (i + 1 < n)
            BreadthFirstSearch(input[i][j], input[i + 1][j], i, j, input);
          if (count >= currentMax) {
            currentMax = count;
            resetResult(input[i][j], input);
          }
        }
      }
    }
  }
  printResult(currentMax, input);
};

displayTable = function (nbLines, nbColumns, nbColors, array) {
  console.log(
    "The generated table with " +
      nbLines +
      " lines, " +
      nbColumns +
      " columns and " +
      nbColors +
      " colors is:\n"
  );

  for (let i = 0; i < nbLines; i++) {
    let line = "";
    {
      for (let j = 0; j < nbColumns; j++) {
        {
          line += array[i][j] + " ";
        }
      }
      console.log(line);
    }
  }
  console.log();
};

printResult = function (res, input) {
  console.log(
    "The largest area contains " + res + " cells as shown below : \n"
  );

  for (let i = 0; i < n; i++) {
    {
      let line = "";
      for (let j = 0; j < m; j++) {
        {
          if (result[i][j] !== 0) line += input[i][j] + " ";
          else line += "- ";
        }
      }
      console.log(line);
    }
  }
};

let input = initTable(n, m, c);
displayTable(n, m, c, input);
FindLargestArea(input);
