# Assignment 8

In this extra credit assignment you will add features to an MVC-based image editing application. You are free to alter any of the classes and/or create new classes as you see fit.

Start with a basic version of the application found in the code included here.

Compile and run the application (the main class is ImageEditor). This basic version of the application provides two "tools". A pixel inspector tool that provides information about a pixel when the mouse is pressed and a paint brush tool that allows you to select a color and to paint on the image with that color. Play around with the application and read the code in order to understand how the application is designed and how it works.

## Features To Add

Now add at least three of the following features. 

* Provide a button in the pixel inspector tool that copies the current pixel information to the paint brush tool as the color chosen for painting.
* Include a "magnifying glass" area in the pixel inspector tool that shows a magnified view of the area around the pixel being inspected.
* Add an undo button that appears at all times that can undo any changes made to the image. You should be able to undo repeatedly until you are back to the original image.
* Add a slider to the paint brush user interface to control the size of the brush.
* Add the ability to choose between a rectangular paint brush and a circular one.
* Modify the paint brush tool to allow the "opacity" of the paint to be controlled. Let opacity be determined as a percentage between 0 and 100 which controls the blending of the paint brush color and the existing color of the pixels being painted.
* Add an "open" button that allows the user to specify a URL from which to load the image being edited. Should replace whatever is being shown at the time.
* Add a new "blur" tool that acts like a paint brush but instead of painting a color it blurs an area of the picture. Include controls for determining the blur area size and shape of blur (i.e., rectangular or circular).
* Add a new "copy and paste" tool that allows the user to specify a "copy" rectangle. When the tool is started, the tool should be in "copy" mode and should allow the user to specify a copy rectangle by clicking on the picture for one corner and dragging to another corner. There should be some visual indication of the copy rectangle on the picture as it is being defined. Once defined, the copy rectangle should be adjustable (i.e., be able to reposition and/or adjust size by grabbing corner handles). At this point, there should be a "copy" button in the interface. Pressing this button will copy the specified area to some internal buffer, remove the visual indication of the copy area, and put the tool into paste mode. In paste mode, mouse clicks on the picture should paste the previously specified copy area at that point. Also in paste mode, there should be a "Reset Copy Area" button to go back to copy mode.


## Grading
The extra credit assignment will count toward improving your overal course grade as follows:

| Number of Features | % points added to course grade |
|--------------------|--------------------------------|
| 3 | 0.5 |
| 4 | 1.0 |
| 5 | 1.5 |
| 6 | 2.0 |
| 7 | 2.5 |
| 8 | 3.0 |
| 9 | 4.0 |

Turn in your code as a "submit" branch. Then fill out this form:

https://goo.gl/forms/Pc5Hrar60ugUIJby1

Whether or not you have successfully completed a particular feature will be judged by me entirely at my discretion and may involve assessing both functionality as well as code design and style.

Although we (i.e., the LAs and myself) may be willing to address quick specific questions about the extra credit assignment, the expectation is that work on the extra credit assignment should be done independently without help in office hours. I will also be looking more closely for code similarity between submissions.
