memo = {}

def fib(n):
    result_from_memo = memo.get(n)
    if result_from_memo != None:
        return result_from_memo
    
    if n <= 1:
        result = 1
    else:
        result = fib(n-1) + fib(n-2)

    memo[n] = result
    return result

