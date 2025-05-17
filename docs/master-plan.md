**Master Plan**

A Java Spring Boot application (Microservice) to manage customer plans and subscriptions.

Users can create and manage their own subdomain under our platform. 
They can choose from different subscription plans—free, 3 months, 6 months, 1 year, or other options based on our setup. 
The system includes authentication and authorization to ensure only verified users access certain features.
**Audit Log**


**Roles**
Roles have different permissions. We can assign roles like:
* **Admin**: Manages core functions
* **Sub-admin**: Helps manage business operations and customer subscriptions
* **Marketing**: Promotes products/services using referral codes and earns from referrals
* **Customer**: Manages their site, subscriptions, billing, products, categories, and services
* **Sub-user**: Assists customers with site management like adding products, services, and categories
* etc .. 

**Permissions**
* Each role has a set of permissions based on its responsibilities
* etc ..

**User Management**
* Each user has a unique username and password
* Each user can have multiple roles, but has default role
* We can assign permission directly to a user without role
* Each user can have own invite code for marketing purposes
* Each user can have referral code under other users
* Each registered user can have a profile
* Each user can have multiple sites
* Each user can have active only one subscription, but keep subscription history
* etc .. 

**Site Management**
* Each site is a subdomain under our main platform
* A customer can create and own multiple sites
* Each site has one owner (customer)
* Multiple users can help manage the same site
* etc ..

**Setting**
store all global setting for planform
**Product**
**Category**
