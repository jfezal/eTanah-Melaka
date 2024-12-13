<%-- 
    Document   : sipu_tandatangan
    Created on : Sep 4, 2012, 5:05:02 PM
    Author     : User
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pembangunan.PuActionBean">
    <s:hidden name="edit1" id="edit1" />
    <s:hidden name="edit2" id="edit2" />
    <s:hidden name="view" id="view" />

    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPCB'}">
                    <table border="0" align="center">
                        <tr>
                            <td><b><font color="#003194">TANDATANGAN SURAT IRINGAN PU    </font></b></td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPCB'}">
                    <legend>Notis / Surat Penyiasatan</legend>
                </c:if>
                <br><br>               
                <div align="left">
                    <p><label>Ditandatangan Oleh :</label></p>
                    <s:select name="sipuTundatangan" id="namapguna">
                                                   <s:option label="Sila Pilih" value="" />                          
                                                   <c:forEach items="${actionBean.penggunaList}" var="i" >                              
                                                           <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                                       </c:forEach>
                                               </s:select>
                    </div>
                    <br/><br/>
                <%--<div id="buttontandatangan" align="center">--%>
                <s:button name="simpanSIPU" class="longbtn" value="Simpan Tandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                <%--</div>--%>
            </div>
        </fieldset>
    </div>
</s:form>