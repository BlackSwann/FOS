class Product < ActiveRecord::Base
  belongs_to :product_type
  has_many :order_details
 # mount_uploader :picture, PictureUploader
end
