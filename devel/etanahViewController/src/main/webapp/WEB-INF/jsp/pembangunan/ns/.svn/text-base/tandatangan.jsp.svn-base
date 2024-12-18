<%-- 
    Document   : tandatangan
    Created on : Apr 9, 2014, 1:01:42 PM
    Author     : khairul.hazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<s:form beanclass="etanah.view.stripes.pembangunan.TarikhTandatanganActionBean">
    <s:messages/>
    <s:errors/>
    
    <c:if test="${actionBean.peringkat eq 'ptd'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Ditandatangani Oleh</legend>
                    <p>
                        <label>Ditandatangani Oleh :</label>
                            <s:select name="tandatangan" style="width:300px;" id="nama_tt">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.listPtdN9}" value="idPengguna" label="nama" />
                            </s:select>
                    </p>                    
                <br><br>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanTandatanganN9" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>                
            </fieldset>
        </div>
    </c:if>
    
    <c:if test="${actionBean.peringkat eq 'ptg'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Tandatangan</legend>
                    <p>
                        <label><font color="red">*</font>Ditandatangani Oleh :</label>
                            <s:select name="tandatangan" style="width:300px;" id="nama_tt">
                                <s:option value="">-- Sila Pilih --</s:option>
                                <s:options-collection collection="${actionBean.listPtgN9}" value="idPengguna" label="nama" />
                            </s:select>
                    </p>                    
                <br><br>
                <p>
                    <label>&nbsp;</label>
                        <s:button name="simpanTandatanganN9" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>                
            </fieldset>
        </div>
    </c:if>
      
</s:form>


