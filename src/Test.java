private Node put(Node h, Key key, Value val) {
  if (h == null) return new Node(key, val, RED); // insert at bootm and color red
  int cmp = key.compareTo(h.key);
  if (cmp < 0) h.left = put(h.left, key, val);
  if (cmp > 0) h.right = put(h.right, key, val);
  if (cmp == 0) h.val = val;
  
  if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
  if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
  if (isRed(h.left) && isRed(h.right)) flipColors(h);
  
  return h;
}