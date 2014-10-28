class Order < ActiveRecord::Base
  belongs_to :order_state

  has_many :order_details
end
