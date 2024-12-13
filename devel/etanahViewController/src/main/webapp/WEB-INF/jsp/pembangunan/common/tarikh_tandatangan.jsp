<%--
    Document   : tarikh_tandatangan
    Created on : Mar 7, 2011, 9:53:48 AM
    Author     : nursyazwani
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
    <c:if test="${actionBean.stageId ne 'rekodtkhtthantarptd'}">         
        <c:if test="${actionBean.peringkat eq 'ptd'}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Ditandatangani Oleh</legend>
                    <p>
                        <label>Ditandatangani Oleh :</label>
                        <s:select name="tandatangan" style="width:300px;" id="nama_tt">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:options-collection collection="${actionBean.listPp}" value="idPengguna" label="nama" />
                        </s:select>
                    </p>
                    <br><br>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanTandatangan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                    <%--</c:if>--%>
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
                            <s:options-collection collection="${actionBean.listPp2}" value="idPengguna" label="nama" />
                        </s:select>
                    </p>                   
                    <br><br>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanTandatangan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </fieldset>
            </div>
        </c:if>
    </c:if>
    <br/>
    <br/>

    <c:choose>
        <c:when test="${actionBean.mohon.kodUrusan.kod ne '425' && actionBean.mohon.kodUrusan.kod ne '425A'}">
            <p><font color="red">*Sila masukkan tarikh tandatangan terlebih dahulu sebelum menekan butang 'Selesai'. </font></p>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tarikh Tandatangan</legend>
                    <p>
                        <label><font color="red">*</font>Tarikh Tandatangan :</label>
                        <s:text name="tarikh" size="12" id="datepicker" class="datepicker"/>
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanTarikh" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </fieldset>
            </div>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>


</s:form>

