# Image Component Labeler

This Java application features a GUI with two matrices. The input matrix is reactive and any changes to it update the output matrix with the corresponding components
comprised of activated input tiles.

![Example GUI Layout](https://github.com/JoelBranham/ImageComponentLabeling/blob/master/presentation/app-overview.PNG)

## Overview
This application displays an input matrix on the right and its corresponding output matrix. Two adjacent tiles belong to a component if they are 
both activated. If a tile is clicked, the output matrix on the right will update the tile's corresponding component. The text at the bottom of the GUI
shows each component's number and its corresponding location on the input/output matrix.
