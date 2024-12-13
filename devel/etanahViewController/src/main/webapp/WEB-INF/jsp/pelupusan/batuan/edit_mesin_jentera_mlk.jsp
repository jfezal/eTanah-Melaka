<%-- 
    Document   : edit_mesin_jentera_mlk
    Created on : Jun 6, 2011, 10:54:32 AM
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
 
    
     function kemaskiniJentera(idJentera)
    {
//        var idJentera = $('#idJentera').val();
//        alert(idJentera) ;
//        if(confirm('Adakah anda pasti?')) {
            alert(idJentera)
            var url = '${pageContext.request.contextPath}/pelupusan/butiran_jentera?updateJentera&idJentera=' + idJentera;
            $.get(url,
            function(data){
                $('#page_div').html(data); 
                self.close();
                opener.refreshJentera() ;
                
           
            },'html');
//        }
    }
    function test(){
         self.close();
        opener.refreshJentera() ;
         
                
    }
    
//    
//    function refreshJentera(){
//                        var url = '${pageContext.request.contextPath}/pelupusan/butiran_jentera?refreshpage';
//                        $.get(url,
//                        function(data){
//                            $('#page_div').html(data);
//                        },'html');
//                    }
//                    
    
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.ButiranJenteraActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Butir-butir Kenderaan/Jentera Yang Digunakan</legend><br/>
            <table>
            <tr>
               <td><label>Jenis Kenderaan/Jentera :</label></td>
               <td><s:text name="jenisJentera" id="jenisJentera" value="${actionBean.jenisJentera}"size="20" maxlength="" onkeyup="this.value=this.value.toUpperCase();"/></td>   
            </tr>
        
            <tr>
                <td><label>No. Pendaftaran :</label></td>
                <td><s:text name="pndftran" value="${actionBean.pndftran}" size="20" onkeyup="this.value=this.value.toUpperCase();"/></td>    
            </tr>
       
            <tr>
                <td><label>Kepunyaan :</label></td>
                <td><s:text name="pemilik" value="${actionBean.pemilik}" size="50" onkeyup="this.value=this.value.toUpperCase();"/></td>
            </tr>
            </table>
            
            <p align="center">
                <s:hidden name="idJentera" id="idJentera" value="${actionBean.idJentera}"/>
                <s:reset name="reset" value="Isi Semula" class="btn"/>
                <%--<s:submit name="simpanJentera" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>--%>
                <s:submit name="updateJentera" value="Kemaskini" class="btn" onclick="test();" />
            </p>
        </fieldset>
    </div>
</s:form>