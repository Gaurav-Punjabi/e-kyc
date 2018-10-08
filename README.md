# Pehchan Kaun - eKYC made easier
To register the users on our portal and verify the user as authentic to the requesting authority or the 3rd party application while preventing data leakage.

### Problem Statement
1. Processing
The supreme court barred certain provisions of the Aadhaar Act – throwing into disarray several business models that rely on Aadhaar-based biometric authentication.  Addressing the concern on data privacy, the latest ruling makes linking of the identification card with private services illegal. This means that the companies that use Aadhaar based e-Know Your Customer (KYC) to acquire new customers, will now have to make changes to how they onboard and verify customers.

2. Proposed System
\t* The participants on our system would be the users and the requesting authorities or 3rd party application.
    
* Our portal will allow the user to register by uploading 2 government identification that include but are not restricted to Aadhaar Card, Driving License and PAN Card. A user profile would be created that will be split into multiple files and stored on various servers. The purpose of this is that the risk of data being leaked from us would be reduced drastically as even if one server gets compromised, it would not lead to the hacker getting all the data but just a part of it. 
    
* The 3rd party application that would be our client can be referred as the requesting authorities herewith are provided with an API that will enable the users to just click their own photo and request one of two services. The first service is that of just verifying if the user is authentic or no. This will be done by running a facial recognition against the users profile which would give us if the user matches to who he claims to be.

* The next service is that of acquiring the data of the user. This would require a good level of security. To try and maximize the security we will send an OTP to the user as a form of consent to send the data to the application that they wants to use. When the user provides their consent, the next step would be to watermark the file. We add this feature as a form of security so that if at all somewhere down the line the user data gets leaked from the requesting authority we can trace it to the application or the authority that might’ve leaked it.

### Screenshots



