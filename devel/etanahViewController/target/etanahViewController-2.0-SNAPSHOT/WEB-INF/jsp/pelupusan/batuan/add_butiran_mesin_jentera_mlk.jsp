<%-- 
    Document   : add_butiran_mesin_jentera_mlk
    Created on : Jun 6, 2011, 10:52:48 AM
    Author     : Akmal
--%>

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
//   function save1(event, f){
//
//                var q = $(f).formSerialize();
//                var url = f.action + '?' + event;
//                $.post(url,q,
//                function(data)
//                {
//                    self.close();
//                    $('#page_div',opener.document).html(data);
//                    
//                },'html');
//                window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=910,height=850");
////               <alert("Maklumat Telah Disimpan") ;>
//        }

function save1(event, f){
     
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshJentera();
                    self.close();
                },'html');
                 alert("Maklumat Telah Disimpan") ;
            
        }
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
   
  

</script>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>


<s:form beanclass="etanah.view.stripes.pelupusan.ButiranJenteraActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Butir-butir Kenderaan/Jentera Yang Digunakan</legend><br/>
            <table>
            <tr>
               <td><label>Jenis Kenderaan/Jentera :</label></td>
               <td><s:text name="jenisJentera" size="20" maxlength="" onchange="javascript:toUpperCase();" onkeyup="this.value=this.value.toUpperCase();"/></td>   
            </tr>
        
            <tr>
                <td><label>No. Pendaftaran :</label></td>
                <td><s:text name="pndftran" size="20" onchange="javascript:toUpperCase();" onkeyup="this.value=this.value.toUpperCase();"/></td>    
            </tr>
       
            <tr>
                <td><label>Kepunyaan :</label></td>
                <td><s:text name="pemilik" size="50" onchange="javascript:toUpperCase();" onkeyup="this.value=this.value.toUpperCase();"/></td>
            </tr>
            </table>

            <p align="center">
                <s:reset name="reset" value="Isi Semula" class="btn"/>
                <%--<s:submit name="simpanJentera" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>--%>
                <s:button name="simpanJentera" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
            </p>
        </fieldset>
    </div>
</s:form>

