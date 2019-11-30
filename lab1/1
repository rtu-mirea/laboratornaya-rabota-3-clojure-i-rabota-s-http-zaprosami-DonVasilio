(defun insert(element position elements)
(if (= position 0)
(cons element elements)
(cons (car elements) (insert element (- position 1) (cdr elements)))
))

(insert 10 4 (list 2 5 8 9 14 17 20))
