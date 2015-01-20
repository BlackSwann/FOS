class OrderDetail < ActiveRecord::Base
  belongs_to :order
  belongs_to :product

  validates :price, :presence => true

end
