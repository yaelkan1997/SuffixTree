# Suffix Tree and Longest Repeated Substring Implementation

## Overview
This project provides a Java implementation of a Suffix Tree data structure along with a specialized extension for finding the Longest Repeated Substring in a given string.

### Classes
- `CharLinkedListImpl`: Implementation of a linked list for characters.
- `CharLinkedListNodeImpl`: Node implementation for the character linked list.
- `SuffixTreeImpl`: Implementation of a general-purpose Suffix Tree for efficient string manipulation.
- `LongestRepeatedSuffixTreeImpl`: Extension of Suffix Tree for finding the Longest Repeated Substring.
- `SuffixTreeNodeImpl`: Implementation of the nodes used in the Suffix Tree.

## Techniques Used
- **Inheritance:**
  - The project employs inheritance to create specialized classes that extend the functionality of the base classes.
  - Examples include `LongestRepeatedSuffixTreeImpl` extending `SuffixTreeImpl` and `SuffixTreeNodeImpl` extending `SuffixTreeNode`.

- **Data Structures:**
  - Utilizes linked lists (`CharLinkedListImpl`) and tree structures (`SuffixTreeImpl`, `LongestRepeatedSuffixTreeImpl`) to efficiently handle and manipulate string data.

- **Algorithmic Techniques:**
  - Employs binary search and recursive algorithms for operations such as searching, adding suffixes, compressing, and counting occurrences in the `SuffixTreeNodeImpl` class.
