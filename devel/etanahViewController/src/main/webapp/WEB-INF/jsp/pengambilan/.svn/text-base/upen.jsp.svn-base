<%-- 
    Document   : Surat Upen
    Created on : 05-Jun-2013, 11:55:43
    Author     : dayana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function refreshPagePenerimaanBorang(){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function updateDetails(h){
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_borang?editDetails&rowCount='+h;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function validation() {
        var namaJUBL   = $("#namaJUBL  ").val();
        var tarikhRujukan  = $("#tarikhRujukan").val();
        var noRujukan   = $("#noRujukan").val();
        var tarikhSuratPenilai  = $("#tarikhSuratPenilai ").val();
        var count  = $("#count ").val();

        for(var i=1;i<=count;i++){
            var namaJUBL  = $("#namaJUBL "+(i - 1)).val();
            var sebabTangguh = $("#sebabTangguh"+(i - 1)).val();
            var tarikhUlangan = $("#tarikhUlangan"+(i - 1)).val();
            var tarikhSuratPenilai = $("#tarikhSuratPenilai"+(i - 1)).val();
          

            if(namaJUBL  == ""){
                alert('Sila masukkan " No Resit Deposit " terlebih dahulu.');
                $("#tarikhBicara"+(i - 1)).focus();
                return false;
            }
            if(tarikhRujukan== ""){
                alert('Sila masukkan " No Resit Fees " terlebih dahulu.');
                $("#sebabTangguh"+(i - 1)).focus();
                return false;
            }
            if(tarikhUlangan == ""){
                alert('Sila pilih " Tarikh Bayaran Deposit " terlebih dahulu.');
                $("#tarikhUlangan"+(i - 1)).focus();
                return false;
            }
            if(tarikhSuratPenilai == ""){
                alert('Sila pilih " Tarikh Bayaran Fees " terlebih dahulu.');
                $("#tarikhSuratPenilai"+(i - 1)).focus();
                return false;
            }
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenerimaanBorangActionBean">

    <s:messages/>
    <div>
        <fieldset class="aras1">
            <legend align="left">
                <b>Sila masukkan maklumat berkaitan diruang yang disediakan.</b>
            </legend>
        </fieldset>
    </div>
    <br>
    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <legend align="center">
                <b>RESIT BAYARAN</b>
            </legend><br/>
            <p align="center">
              <%--  <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_borang" id="line">--%>

              <%-- <c:if test="${line_rowNum-1 == 0}">--%>
            
            <fieldset class="aras1" ><br/>
                 
                 <b>Resit Bayaran Deposit</b>
                 <br><br>
                 <div align="left">
                     <p align="right"> <label style="text-align:right ">Tarikh Resit  :</label></p>
                            <%--  <fmt:formatDate value="${actionBean.tarikhSuratPenilai}" pattern="dd/MM/yyyy"/>&nbsp;--%>
                            <s:text style="text-align:left;width:15%" name="tarikhRujukan"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhRujukan" value="actionBean.tarikhRujukan"/>
                         <%--   <s:hidden name="count" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="count"/>--%>
                         <br><br>

                            <label style="text-align:right">Resit No :</label>
                            <s:text style="text-align:left;width:15%" name="namaJUBL" id="namaJUBL" value="namaJUBL" class="normal_text"/>
</div>
                   <%-- </c:if>--%>
             </fieldset>
         

                <br/><br/><br/>
             <%--   <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="6" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/penerimaan_borang" id="line">--%>
                 <%--   <c:if test="${line_rowNum-1 == 0}">--%>
                  <fieldset class="aras1"><br/>
                      <b>Resit Bayaran Fee</b>
                      <br><br>
                      <div align="left">
                          <p align="right">  <label style="text-align:right">Tarikh Resit :</label></p>
                            <s:text style="text-align:left;width:15%" name="noRujukan"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="noRujukan" value="actionBean.noRujukan"/>
                          <%--  <s:hidden name="count" value="${fn:length(actionBean.tarikhSuratPenilai)}" id="count"/>--%>
                 
                          <br><br>
                          <p align="right">  <label style="text-align:right">Resit No :</label></p> 
                            <s:text style="text-align:left;width:15%" name="catatan" id="catatan" value="catatan" class="normal_text"/>
                       <br><br>
                       <p align="right">  <label style="text-align:right">Amaun (RM):</label></p> 
                       <s:text style="text-align:left;width:15%" name="ulasan" id="ulasan" value="ulasan" class="normal_text"/>
                   <%--</c:if>--%>
               <br><br>
                <%--<s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                <p align="center">  <s:button name="simpanUPEN" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/></p> 
</div>
                <br/>
                <br/>
                <br/>

        </fieldset>
    </div>
   
</s:form>
