/*
Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

 

Example 1:


Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
Output: 8 
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
Example 2:


Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
Output: 6
Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
Example 3:

Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
Output: 0
 

Constraints:

1 <= n <= 105
edges.length == n - 1
edges[i].length == 2
0 <= ai < bi <= n - 1
fromi < toi
hasApple.length == n
*/

class Solution 
{
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) 
    {
        List<Integer>[] canVisit = new ArrayList[n];
        
        //Creating adjacency list
        for(int i = 0; i<n; i++)
        {
            canVisit[i] = new ArrayList<>();
        }
        for(int[] edge : edges)
        {
            canVisit[edge[0]].add(edge[1]);            
            canVisit[edge[1]].add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        return helper(0,hasApple,canVisit,visited);
        
    }
    int helper(int i, List<Boolean> hasApple, List<Integer>[] canVisit, boolean[] visited)
    {
        if(visited[i]) return 0;
        visited[i] = true;
        
        // Visiting every child node
        int sum = 0;
        for(int edge : canVisit[i])
        {
            sum += helper(edge, hasApple, canVisit, visited);
        }
        
        if(i==0) return  sum; // Special case for the root node
        
        // If any children contain an apple or the current node contains an apple we return (2+sum)
        if(hasApple.get(i) || sum>0 ) return (2 + sum); 
        
        //If neither the children or the current node contain an apple we just return 0
        else return 0;        
    }
}
