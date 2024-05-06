import sys
from sympy import Matrix

# Recibe los parámetros desde la línea de comandos
modulo = int(sys.argv[1])
matrix_elements = sys.argv[2:]  # Los elementos de la matriz son los argumentos restantes

# Convierte los elementos de la matriz en enteros
matrix_elements = [int(elem) for elem in matrix_elements]

# Calcula la dimensión de la matriz cuadrada
n = int(len(matrix_elements) ** 0.5)

# Construye la matriz de SymPy a partir de los elementos proporcionados
A = Matrix(n, n, matrix_elements)

# Calcula la inversa modular de A
A_inv = A.inv_mod(modulo)

# Imprime la inversa de A
print(A_inv)

