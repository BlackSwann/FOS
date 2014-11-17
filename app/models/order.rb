class Order < ActiveRecord::Base
  belongs_to :order_state

  has_many :order_details

 # attr_accessible :order_details_attributes
accepts_nested_attributes_for :order_details, :allow_destroy => true


  #before_create :build_order_details

  def build_order_details
      self.order_details.build
  end
end
