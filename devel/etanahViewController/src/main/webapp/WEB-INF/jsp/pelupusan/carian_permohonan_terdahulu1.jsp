<%-- 
    Document   : carian_permohonan_terdahulu1
    Created on : June 17, 2011, 3:23:57 PM
    Author     : Murali
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
</head>
<script type="text/javascript">
      $(document).ready( function() {

     <%--  maximizeWindow();--%>
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
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

    function save1(event, f){
          
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);                      
                   // self.opener.refreshRizab();
                   opener.refreshlptn();
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

<s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanah4ActionBean" name="test">
    <s:errors/>
    <s:messages/>
    <div class="subtitle1">
        <c:if test="${!status}">
        <fieldset class="aras1">
            <legend>Carian Permohonan</legend>

            <p>
                <label for="id Permohonan">Id Permohonan:</label>
                <s:text name="id" id="id" class="required"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:submit name="cariPermohonan" value="Cari" class="btn" onclick=""/>
                &nbsp;<s:reset name="IsiSemula" value="Isi Semula" class="btn" onclick=""/>
                &nbsp;<s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                <%--<s:button name="cariPermohonan" id="cari" value="Cari" class="btn" onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>--%>
                <%--<s:submit name="cariPermohonan" id="cari" value="Test" class="btn" onclick="return validate_required(this.form)"/>--%>
                  
            </p>
        </fieldset>
        </c:if>
        </div>
    <div class="content" align="center">
        <c:if test="${status}">
        <fieldset class="aras1">
            <legend>Status Permohonan ( ${actionBean.prmhnn.kodUrusan.nama} )</legend>
            <s:hidden name="id2" value="id"/>
            <table border="0" width="60%" cellspacing="7" align="right">

                <tr>
                    <td align="left" width="20%">Status Keputusan</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black"><c:if test="${actionBean.prmhnn.keputusan.nama ne null}">${actionBean.prmhnn.keputusan.nama}</c:if>
                        <c:if test="${actionBean.prmhnn.keputusan.nama eq null}">Tiada Maklumat</c:if></td>
                </tr>
                 <tr>
                    <td align="left">Tarikh Keputusan</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black"><c:if test="${actionBean.prmhnn.tarikhKeputusan ne null}">
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.prmhnn.tarikhKeputusan}"/>
                            </c:if>
                        <c:if test="${actionBean.prmhnn.tarikhKeputusan eq null}">Tiada Maklumat</c:if></td>
                </tr>
                 <tr>
                    <td align="left">Keputusan Oleh</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black"><c:if test="${actionBean.prmhnn.keputusanOleh.nama ne null}">${actionBean.prmhnn.keputusanOleh.nama}</c:if>
                        <c:if test="${actionBean.prmhnn.keputusanOleh.nama eq null}">Tiada Maklumat</c:if></td>
                </tr>
                 <tr>
                    <td align="left">Pemohon</td>
                    <td style="color:black">:</td>
                    <td align="left" style="color:black"><c:if test="${actionBean.pemohon.pihak.nama ne null}">${actionBean.pemohon.pihak.nama} <br>
                                                                              ${actionBean.pemohon.pihak.noPengenalan} </c:if>
                        <c:if test="${actionBean.pemohon.pihak.nama eq null}">Tiada Maklumat</c:if></td>
                </tr>
                <tr>
                    <td align="left" colspan="3">
                        <c:if test="${!simpan}">
                            <s:button name="simpanPermohonanSblm" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>
                            <%--<s:button name="simpanPermohonanSblm" value="Simpan" class="btn"/>--%>
                        </c:if>
                        &nbsp;<s:submit name="tutupPermohonan" value="Tutup" class="btn" onclick="test1();"/>
                        &nbsp;<s:submit name="cariSemula" value="Cari Semula" class="btn" onclick=""/>                        
                    </td>
                 </tr>
            </table>                
       </fieldset>
               <%-- <fieldset class="aras1">
                    <legend>Hakmilik Permohonan</legend>
                 <display:table class="tablecloth" name="${actionBean.hmpList}" cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:baseline"/>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                 </display:table>
                <br><br>
                <c:if test="${!simpan}">
                 <s:button name="simpanPermohonanSblm" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                 <s:button name="cariSemula" id="cari" value="Cari Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </fieldset>
--%>
        
        </c:if>
    </div>


</s:form>
