<%--
    Document   :  laporan_tanahGSAKeadaanTanah.jsp
    Created on :  Jan 17, 2012, 10:50:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL TANAH</title>
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
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
   
    $(document).ready(function() {
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    }); //END OF READY FUNCTION
    
    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function showptgsasokong(){
        $('#ptgsasokong').show();
        $('#ptgsatidaksokong').hide();
    }
    
    function showptgsatidaksokong() {
        $('#ptgsatidaksokong').show();
        $('#ptgsasokong').hide();
    }
    
    function addRow(index,f)
    {   
        NoPrompt();
        var kodksn= document.getElementById("ksn").value;
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?tambahRow&index='+index+'&kodksn='+kodksn;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function menyimpan(index,i,f)
    {
        /*
         * LEGEND : 5 -> MohonLaporUlas*
         */
        NoPrompt();
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan2"+i).value;
        if(index == 22)
            kand = document.getElementById("kandungan22"+i).value;
        if(index == 3)
            kand = document.getElementById("kandungan3"+i).value;
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==5){
            kand = document.getElementById("kandungan5"+i).value;
        }
        if(index==6){
            kand = document.getElementById("kandungan6"+i).value;
        }
        if(index==7){
            kand = document.getElementById("kandungan7"+i).value;
        }
        if(index==8){
            kand = document.getElementById("kandungan8"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        var kodksn= $('#ksn').val();
        var idHakmilik= $('#idHakmilik').val();
        var sizeSenaraiLaporUlas = "0";
    <c:if test="${actionBean.sizeSenaraiLaporUlas ne null}">
            var sizeSenaraiLaporUlas= $('#sizeSenaraiLaporUlas').val();              
    </c:if>
            //         alert(sizeSenaraiLaporUlas);
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?simpanKandungan&idHakmilik='+idHakmilik+'&index='+index+'&kandungan='+kand+'&kodksn='+kodksn+'&sizeLU='+sizeSenaraiLaporUlas;   
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        function deleteRow(idKandungan,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?deleteRow&idKandungan='+idKandungan+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);

                }, 'html');
                self.refreshpage2('syorPPT') ;
            }
        }
        function setKpsn(val)
        {
            $('#ksn').val(val);
        }
    
        function refreshpage(){
            //        alert('aa');
            NoPrompt();
            opener.refreshGSA('main');
            self.close();
        }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahGSAPelupusanActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="ksn" id="ksn"/>
        <s:hidden name="sizeSenaraiLaporUlas" id="sizeSenaraiLaporUlas"/>

        <div class="subtitle">
            <fieldset class="aras1">
                <div id="perihaltanah">
                    <legend>
                        <c:if test="${actionBean.kodNegeri eq '04'}">Syor Penolong Pegawai Tanah</c:if>
                        <c:if test="${actionBean.kodNegeri eq '05'}">Ulasan Penolong Pegawai Tanah</c:if>
                        </legend>                
                    <%--PTGSA CASE--%>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                        <div id="syorPPT" align="center">
                            <table class="tablecloth" align="center">
                                <tr>
                                    <td>
                                        Syor :
                                    </td>
                                    <td colspan="2">
                                        <s:radio name="ksn" id="ksn" value="SL" onclick="setKpsn(this.value)"/>&nbsp;Sokong
                                        <s:radio name="ksn" id="ksn" value="ST" onclick="setKpsn(this.value)"/>&nbsp;Tidak Sokong
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Ulasan :
                                    </td>
                                    <td colspan="2">
                                        <%--${fn:length(actionBean.senaraiLaporanKandungan1)}&nbsp;--%>
                                    </td>
                                </tr>
                                <c:set var="i" value="1" />
                                <c:set var="num" value="1"/>
                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr>
                                        <td style="text-align: right"><c:out value="${num})"/></td>
                                        <td>
                                            <s:textarea  id="kandungan5${i}"name="senaraiLaporanKandungan1[${i-1}].ulasan" cols="80"  rows="5" class="normal_text"/>                                        
                                        </td>
                                        <td>
                                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idLaporUlas},this.form,'mohonLaporUlas')"></s:button> 
                                            </td>
                                        </tr>
                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="num" value="${num+1}"/>
                                </c:forEach>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td colspan="2">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('5',this.form)"></s:button> 
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"></s:button>
                                        <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                    </td>

                                </tr>
                            </table>
                        </div>
                    </c:if>
                </div>        
            </fieldset>
        </div>
    </s:form>
</body>

