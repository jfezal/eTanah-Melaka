<%-- 
    Document   : popup_notis_sita
    Created on : Jul 26, 2012, 9:36:54 AM
    Author     : Siti Fariza Hanim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<script type="text/javascript">
    function validate(){
        if($('#kesalahan').val() == ''){
            alert("Sila masukkan Kesalahan.");
            $('#kesalahan').focus();
            return false;
        }
        return true;
    }

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshNotisSita();
            self.close();
        },'html');

    }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatBarangTahananActionBean">
    <s:messages/>
    <div class="subtitle">
        <c:if test="${edit}">
            <fieldset class="aras1">
                <s:hidden name="idBarang" id="idBarang"></s:hidden>
                <div>
                    <legend>Notis Sita</legend>

                    <label>Butiran Kesalahan :</label>
                    <s:textarea name="kesalahan" id="kesalahan" cols="60" rows="20" class="normal_text"/>
                </div>
            </fieldset>
            <br/>
            <p align="center">
                <s:button class="btn"  name="simpanNotisSita" onclick="if(validate())save(this.name,this.form);" value="Simpan"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </c:if>
        <c:if test="${view}">
            <fieldset class="aras1">
                <s:hidden name="idBarang" id="idBarang"></s:hidden>
                <div>
                    <legend>Notis Sita</legend>
                    <label>Butiran Kesalahan :</label>
                    <s:textarea name="kesalahan" id="kesalahan" cols="60" rows="20" class="normal_text" readonly="true"/>
                    <%--<table>
                        <tr>
                            <td valign="top">
                                <p><label>Kesalahan :</label></p></td>
                            <td valign="top">
                                <font size="2px;" color="black" style="font-style: normal; font-weight: normal" >
                                    <c:if test="${actionBean.barangRampasan.kesalahan ne null}">${actionBean.barangRampasan.kesalahan}</c:if>
                                    <c:if test="${actionBean.barangRampasan.kesalahan eq null}"> Tiada Data </c:if></font></td>
                        </tr>
                    </table>--%>
                    <p align="center">
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>

                    </p>
                </div>
            </fieldset>
        </c:if>

    </div>
</s:form>
