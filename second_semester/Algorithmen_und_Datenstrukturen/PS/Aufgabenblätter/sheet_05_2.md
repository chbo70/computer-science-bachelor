A binary tree has 1 or 2 children at non-leaf nodes and 0 nodes at leaf nodes. Let there be $n$ nodes in a tree and we have to arrange them in such a way that they still form a valid binary tree.

Without proving, I am stating that to maximize the height, given nodes should be arranged linearly, i.e. each non-leaf node should have only one child:

```
                              O 1                               |                               O 2                               |                               O 3                               |                               O 4                               |                               O 5                               |                               O 6                               |                               O 7                               |                               O 8 
```

Here, formula to compute relation of height in terms of number of nodes is straight-forward. If $h$ is the height of the tree, then $h = n-1$.

Now, if we try to construct a binary tree of $n$ nodes with minimum height (always reducible to a complete binary tree), we have to pack as many nodes as possible in upper levels, before moving on to the next level. So, the tree takes the form of following tree:

```
                              O                               |1                               |                        O------+-----O                        |2           |3                        |            |                    O---+---O    O---+----O                    |4      |5    6        7                    |       |                O---+--O    O                 8      9    10 
```

Let us start with a particular case, $n = 2^m - 1$.

We know that, $2^0 + 2^1 + 2^2 + ... + 2^{m-1} = 2^m - 1$ 

Also, it is easy to prove that, a level $i$ can have at most $2^i$ nodes in it.

Using this result in the above sum, we find that for each level $i$, from $0$ to $m$, there exists a corresponding term $2^{i-1}$ in the expansion of $2^m - 1$. This implies, that a complete binary tree $2^m - 1$ nodes is completely filled and has height, $h(2^m-1) = m-1$, where $h(n) = $ height of a complete binary tree with $n$ nodes.

Using this result, $h(2^m) = m$, since tree with $2^m-1$ nodes is completely filled and thus a tree with $(2^m-1)+1 = 2^m$ nodes has to accomodate the extra node in the next level $m$, increasing the height by 1 from $m-1$ to $m$.

Until now we have proved, $h(2^m) = m,~ h(2^{m+1}) = m+1$  as well as, $$h(2^{m+1} -1) = m$$

Thus, $\forall n \in \mathbb{Z}, 2^m \leq n < 2^{m+1}$m \leq h(n) < m+1$

But, taking log (base 2) on both sides, $$m \leq \log_2(n) < m+1$$ $$m = \lfloor \log_2(n) \rfloor$$

Thus, $\forall n, n \in [2^m, 2^{m+1})$ $h(n) = m = \lfloor \log_2(n) \rfloor$ 

And we can generalize this result $\forall n \in \mathbb{Z}$ using induction.

PS: The book that states height of a complete binary tree as $\log_2(n+1)-1$ is not valid for all $n$ because $\log_2(n)$ would give non-integral values for most integers $n$ (i.e. for all but perfect binary trees), but height of a tree is purely integral.
