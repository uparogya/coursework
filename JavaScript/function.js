function foldLeft(L, f){
    let result = f(L[0], L[1])
    for (let i = 2; i < L.length; ++i){
        result = f(result, L[i])
    }
    return result
}

const inputs = []

function callFunction() {
    // const L = [2, 3, 5, 3, 7, 2]
    num = document.getElementById("input").value
    inputs.push(+num)
    document.getElementById("display_nums").innerText +=  num + ', '
}

function callSum() {
    const sum = foldLeft(inputs, (x,y)=> x+y )
    document.getElementById("display_sum").innerText = 'SUM = ' + sum
}