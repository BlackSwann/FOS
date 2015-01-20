class Order < ActiveRecord::Base
  belongs_to :order_state

  has_many :order_details
  belongs_to :table

 # attr_accessible :order_details_attributes
accepts_nested_attributes_for :order_details, :allow_destroy => true, :reject_if => :all_blank, reject_if: :invalid_amount
accepts_nested_attributes_for :order_details, :allow_destroy => true, :reject_if => :all_blank, reject_if: :invalid_product

  validates :table, :presence => true


  #before_create :build_order_details

  def build_order_details
      self.order_details.build
  end

  private
  def invalid_amount(attributes)
	  attributes['amount'].blank?
  end
  def invalid_product(attributes)
	  attributes['product'].blank?
  end

end
