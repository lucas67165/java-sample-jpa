Table users {
  id bigint [pk, increment]
  full_name varchar(191)
  full_name_en varchar(191)
  username varchar(30)
  email varchar(191) [not null, unique]
  password_hash varchar(191) [not null]
  gender smallint [default: 1, note: "1: male, 2: female, 3: other"]
  date_of_birth date
  id_card_number varchar(30)
  id_card_issue_date date
  marital_status tinyint [default: 1]
  profile_photo varchar(191)
  user_type tinyint [default: 0]
  is_enabled boolean [not null, default: true]
  created_by varchar(50)
  updated_by varchar(50)
  created_at timestamp
  updated_at timestamp
  account_non_expired boolean [default: true]
  account_non_locked boolean [default: true]
  credentials_non_expired boolean [default: true]
  expiration_date date
}

Table sites {
  id bigint [pk, increment]
  title varchar(191)
  logo_url varchar(191)
  site_url varchar(191)
  theme_color varchar(7) [note: "Hex color code"]
  created_at timestamp
  updated_at timestamp
}

Table user_sites {
  user_id bigint [ref: > users.id]
  site_id bigint [ref: > sites.id]
  site_role tinyint [note: "1: owner, 2:editor"]
  primary key (user_id, site_id)
}

Table settings {
  id bigint [pk, increment]
  setting_key varchar(191) [unique]
  setting_value text
}

Table categories {
  id bigint [pk, increment]
  user_id bigint [ref: > users.id]
  category_name varchar(191)
  status tinyint [default: 1, note: "1: active, 0: inactive"]
  created_at timestamp
  updated_at timestamp
}

Table products {
  id bigint [pk, increment]
  user_id bigint [ref: > users.id]
  category_id bigint [ref: > categories.id]
  product_name varchar(191)
  description text
  image_url varchar(191)
  visit_count int [default: 0]
  add_to_cart_count int [default: 0]
  created_at timestamp
  updated_at timestamp
}

Table site_statistics {
  id bigint [pk, increment]
  site_id bigint [ref: > sites.id]
  platform varchar(191)
  guest_platform_id varchar(191)
  ip_address varchar(45)
  referral_link varchar(191)
  created_at timestamp
  updated_at timestamp
}

