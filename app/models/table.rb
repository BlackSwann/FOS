class Table < ActiveRecord::Base
  belongs_to :table_state
  has_many :orders

  def to_s
	  number
  end
end
