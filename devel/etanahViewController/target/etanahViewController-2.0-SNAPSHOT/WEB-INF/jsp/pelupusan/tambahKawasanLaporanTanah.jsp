<%-- 
    Document   : carian_permohonan_terdahulu
    Created on : Dec 09, 2010, 3:23:57 PM
    Author     : Rohan
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<head>
    
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker-ms.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
</head>
<script type="text/javascript">
   
    function validate()
{
    a = document.test.id ;
  
  if (document.test.id.value==null||document.test.id.value=="")
    {

    alert("Sila masukkan id permohonan");
    a.focus()
    return false;
    }
  else
    {
     
    return true ;
    }
  
}

function test1(){
    
    self.close();

    
    
    
    
    
  
}
function openLain_lain(val){
      if(val == '99'){
           $('#catatanKwsn').show();
      }else{
           $('#catatanKwsn').hide();
      }
}
    function save1(event, f){
          
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    //self.opener.refreshRizab();
                    opener.refreshlptn();
                   self.close();
                },'html');
            
        }
          $(document).ready(function() {
            maximizeWindow(),$('#catatanKwsn').hide(),$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),
            $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanah4ActionBean" name="test">
    <s:errors/>
    <s:messages/>
    
    <div class="content" align="center">
        <fieldset class="aras1">
            <legend>Tanah Dipohon Berada Dalam Kawasan :</legend>
            <s:hidden name="id2" value="id"/>
            <table border="0" width="60%" cellspacing="7" align="center">

                <tr>
                    <td align="left" width="20%">Jenis Rizab :</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black">
                        <s:select name="kodRizab" onchange="openLain_lain(this.value)">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr id="catatanKwsn">
                    <td align="left">Catatan</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black">
                        <s:text name="addcatatan" size="30" />
                    </td>
                </tr>
                 <tr>
                    <td align="left">No Warta</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black">
                        <s:text name="addnoWarta" size="30" />
                    </td>
                </tr>
                 <tr>
                    <td align="left">Tarikh Warta</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black">
                        <s:text name="addtarikhWarta" size="12" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/> <em>[hh/bb/tttt]</em>
                    </td>
                </tr>
                 <tr>
                    <td align="left">No Pelan Warta</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black">
                        <s:text name="addnoPelanWarta" size="30" />
                    </td>
                </tr>
                <tr>
                    <td align="left" colspan="3">
                        <c:if test="${!simpan}">
                            <s:button name="simpanKawasan" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>
                        </c:if>
                        &nbsp;<s:submit name="tutupPermohonan" value="Tutup" class="btn" onclick="test1();"/>                
                    </td>
                 </tr>
            </table>                
       </fieldset>
    </div>
</s:form>
