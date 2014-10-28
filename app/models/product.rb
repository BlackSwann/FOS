class Product < ActiveRecord::Base
  belongs_to :product_type
  has_many :order_details
end
