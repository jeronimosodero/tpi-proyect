package classes;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("handle")
@Expose
private String handle;
@SerializedName("type")
@Expose
private String type;
@SerializedName("link")
@Expose
private String link;
@SerializedName("expand")
@Expose
private List<String> expand = null;
@SerializedName("lastModified")
@Expose
private String lastModified;
@SerializedName("parentCollection")
@Expose
private Object parentCollection;
@SerializedName("parentCollectionList")
@Expose
private Object parentCollectionList;
@SerializedName("parentCommunityList")
@Expose
private Object parentCommunityList;
@SerializedName("bitstreams")
@Expose
private Object bitstreams;
@SerializedName("archived")
@Expose
private String archived;
@SerializedName("withdrawn")
@Expose
private String withdrawn;

/**
* 
* @return
* The id
*/
public Integer getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(Integer id) {
this.id = id;
}

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The handle
*/
public String getHandle() {
return handle;
}

/**
* 
* @param handle
* The handle
*/
public void setHandle(String handle) {
this.handle = handle;
}

/**
* 
* @return
* The type
*/
public String getType() {
return type;
}

/**
* 
* @param type
* The type
*/
public void setType(String type) {
this.type = type;
}

/**
* 
* @return
* The link
*/
public String getLink() {
return link;
}

/**
* 
* @param link
* The link
*/
public void setLink(String link) {
this.link = link;
}

/**
* 
* @return
* The expand
*/
public List<String> getExpand() {
return expand;
}

/**
* 
* @param expand
* The expand
*/
public void setExpand(List<String> expand) {
this.expand = expand;
}

/**
* 
* @return
* The lastModified
*/
public String getLastModified() {
return lastModified;
}

/**
* 
* @param lastModified
* The lastModified
*/
public void setLastModified(String lastModified) {
this.lastModified = lastModified;
}

/**
* 
* @return
* The parentCollection
*/
public Object getParentCollection() {
return parentCollection;
}

/**
* 
* @param parentCollection
* The parentCollection
*/
public void setParentCollection(Object parentCollection) {
this.parentCollection = parentCollection;
}

/**
* 
* @return
* The parentCollectionList
*/
public Object getParentCollectionList() {
return parentCollectionList;
}

/**
* 
* @param parentCollectionList
* The parentCollectionList
*/
public void setParentCollectionList(Object parentCollectionList) {
this.parentCollectionList = parentCollectionList;
}

/**
* 
* @return
* The parentCommunityList
*/
public Object getParentCommunityList() {
return parentCommunityList;
}

/**
* 
* @param parentCommunityList
* The parentCommunityList
*/
public void setParentCommunityList(Object parentCommunityList) {
this.parentCommunityList = parentCommunityList;
}

/**
* 
* @return
* The bitstreams
*/
public Object getBitstreams() {
return bitstreams;
}

/**
* 
* @param bitstreams
* The bitstreams
*/
public void setBitstreams(Object bitstreams) {
this.bitstreams = bitstreams;
}

/**
* 
* @return
* The archived
*/
public String getArchived() {
return archived;
}

/**
* 
* @param archived
* The archived
*/
public void setArchived(String archived) {
this.archived = archived;
}

/**
* 
* @return
* The withdrawn
*/
public String getWithdrawn() {
return withdrawn;
}

/**
* 
* @param withdrawn
* The withdrawn
*/
public void setWithdrawn(String withdrawn) {
this.withdrawn = withdrawn;
}

}
