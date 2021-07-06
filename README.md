# Tab-Android-App

Tab-Android-App is an android java app with 4 tabs using ViewPager.   
The pages include:  
- **MAIN**:  tap to display area, current time, temperature, weather and more.
- **CONTACTS**: includes save, delete, and bookmark phone addresses
- **ALBUM**: gallery page with album folders
- **COMMUTE**: enables to check commute via app 

## Page Explanation
#### 0. Tap
By connecting ViewPager2 and Tablayer, the fragment can be switched.

#### 1. MAIN   
A page that displays the region, current time, temperature, weather, etc...
* Get system time and print it
* Display various time and weather information using OpenWeatherMap API and Volley

#### 2. CONTACTS
Phone address page with save, delete, and bookmark features.   
* Store names and contact information using SQLite database
* Listing contact information using recycler view
* Implement Add, Wish, and Delete buttons
* Press the phone button to switch to the phone screen.
* Implement swipe refresh function

<p float="left">
<img src="https://user-images.githubusercontent.com/49232148/124530748-11a57600-de48-11eb-89d2-36b39495d94b.jpg" width="400" height="800">
<img src="https://user-images.githubusercontent.com/49232148/124530744-10744900-de48-11eb-9a8a-4e6caff4846d.jpg" width="400" height="800">
</p>

#### 3. ALBUM   
intent를 사용해서 카테고리별 앨범 activity로 전환함

#### 4. COMMUTE 
Page that enables to check commute via app.    
The user can press the commute button if the user is within 100m distance from the company.    
Features include: 
* Integration with **Google Map API**   
* Getting and updating current location   
* Calculate hours of duty using the commute button   
<p float="left">
<img src="https://user-images.githubusercontent.com/49232148/124530752-123e0c80-de48-11eb-96fd-70ce6ffcc236.jpg" width="400" height="800">
<img src="https://user-images.githubusercontent.com/49232148/124530756-12d6a300-de48-11eb-90c5-35c23bc0e71f.jpg" width="400" height="800">
</p>




## Getting Started

## Contacts
Contributors
- Yeeun Song, yeeunsong1019@gmail.com
- Taewoo Kim, rlaxodntttt@kaist.ac.kr

