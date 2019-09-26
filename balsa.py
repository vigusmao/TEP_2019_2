E = 'E'
D = 'D'

memo = set()

def backtrack(sequencia, ocupado_esq, ocupado_dir, fila, L, seq_otima):
    # o estado atual já foi visto antes?
    #representacao = (max(ocupado_esq, ocupado_dir), len(sequencia))
    representacao = tuple(sequencia)
    if representacao in memo:
        return  # nada mais a se fazer, esse estado já foi visitado

    # o estado corrente merece tratamento especial?
    if len(sequencia) > len(seq_otima):
        seq_otima.clear()
        for elemento in sequencia:
            seq_otima.append(elemento)

    # para cada proximo passo possível...
    tamanho_prox = fila[len(sequencia)]
    # esquerda?
    if (ocupado_esq + tamanho_prox) <= L:
        sequencia.append(E)
        ocupado_esq += tamanho_prox
        backtrack(sequencia, ocupado_esq, ocupado_dir, fila, L, seq_otima)
        sequencia.pop()
        ocupado_esq -= tamanho_prox
    # direita?
    if (ocupado_dir + tamanho_prox) <= L:
        sequencia.append(D)
        ocupado_dir += tamanho_prox
        backtrack(sequencia, ocupado_esq, ocupado_dir, fila, L, seq_otima)
        sequencia.pop()
        ocupado_dir -= tamanho_prox

    memo.add(representacao)
    

# Main

fila = eval(input("Digite a fila: "))
print(fila)
L = int(input("Digite L: "))

seq_otima = []  # output
backtrack([], 0, 0, fila, L, seq_otima)

print(seq_otima)
print("%d carros embarcados" % len(seq_otima))


