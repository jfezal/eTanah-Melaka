<%-- 
    Document   : kertasMMKHuraianPtg
    Created on : Jun 16, 2013, 10:56:15 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perakuan/Syor Pengarah Tanah dan Galian</title>
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
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKPBGSA?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function addRow(index,f)
    {   
        NoPrompt();
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_MMKPBGSA?tambahRow&index='+index;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function deleteRow(idKandungan,f,tName)
    {   
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/kertas_MMKPBGSA?deleteRow&idKandungan='+idKandungan+'&tName='+tName+'&typeName=kHuraianPtg',q,
            function(data){
                $('#page_div').html(data);

            }, 'html');
            self.refreshpage2('kHuraianPtg') ;
        }
    }
    
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2KertasMMK('main');
        self.close();
    }
        
    function setKandungan(i,idKandungan){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        $('#'+idKandungan+'kandunganKertas').val(kandungan);
    }
    
    function checkKandungan(i){
        var index = i;
        var kandungan = $('#kandungan5'+index).val();
        if(kandungan == 'Sila Tambah Ayat Di Sini'){
            $('#kandungan5'+index).val("");
        }
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
    <s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKPBGSAActionBean" name="form">
        <s:errors/>
        <s:messages/>    
        <div class="subtitle">
            <fieldset class="aras1">
                    <legend>Huraian Pengarah Tanah dan Galian</legend>
                <table class="tablecloth" border="0" align="center">
                    <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiKandunganHuraianPtg}" var="line">
                        <tr>
                            <td style="text-align: right"><c:out value="${num}"/></td>
                            <td>
                                <s:textarea  id="kandungan5${i}" name="senaraiKandunganHuraianPtg[${i-1}].kandungan" cols="80"  rows="5" onclick="checkKandungan(${i})" onblur="setKandungan(${i},${line.idKandungan})" class="normal_text"/>
                                <s:hidden id="${line.idKandungan}kandunganKertas" name="${line.idKandungan}kandunganKertas"/>
                                <s:hidden name="idKandungan1" id="idKandungan1" value="${line.idKandungan}"/>
                            </td>
                            <td style="vertical-align: middle;">
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form,'mohonKertasKandungan')"></s:button> 
                                </td>
                            </tr>

                        <c:set var="i" value="${i+1}" />
                        <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                    <tr>
                        <td style="text-align:center;" colspan="3">      
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('9',this.form)"/>
                            <s:submit name="simpanKandungan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                            <s:hidden name="indexKertas" id="indexKertas" value="9"/>
                            <%--<s:button name="simpanSyor" value="Simpan" class="btn" onclick="menyimpan('5',${i-1},this.form)"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </s:form>
</body>
