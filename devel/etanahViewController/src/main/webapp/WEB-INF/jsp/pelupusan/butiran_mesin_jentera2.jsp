<%-- 
    Document   : butiran_mesin_jentera2
    Created on : Aug 3, 2010, 10:53:34 AM
    Author     : sitifariza.hanim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript">
   function save1(event, f){

                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    self.close();
                    $('#page_div',opener.document).html(data);
                    
                },'html');
               <%--  alert("Maklumat Telah Disimpan") ;--%>

        }
</script>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>


<s:form beanclass="etanah.view.stripes.pelupusan.ButiranBahanBatuanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Butir-butir Kenderaan/Jentera Yang Digunakan</legend><br/>
             <p>
                 <label>Jenis Kenderaan/Jentera :</label>
                 <s:text name="jenisJentera" size="5" maxlength="1" onchange="javascript:toUpperCase();"/>
            </p>
            <p>
                <label>No. Pendaftaran :</label>
                <s:text name="pndftran" size="50"/>
            </p>
       <%--      <p>
                <label>Pemilik Kenderaan/Jentera :</label>
                <s:text name="pemilik" size="50"/>
            </p>--%>
            <br/>

            <p align="center">
                <s:reset name="reset" value="Isi Semula" class="btn"/>
                <s:submit name="simpanJentera" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>
            </p>
        </fieldset>
    </div>
</s:form>





