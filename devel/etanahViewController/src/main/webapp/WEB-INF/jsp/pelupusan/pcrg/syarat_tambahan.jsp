<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    
    function menyimpan(index,i,f){
        var kand;
        if(index==1){
            kand = document.getElementById("kandungan1"+i).value;
        }
        if(index==3){
            kand = document.getElementById("kandungan3"+i).value;
        }
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        if(index==10){
            kand = document.getElementById("kandungan10"+i).value;
        }
        var viewMMKN = $('#viewMMKN').val();
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/syaratTambahan?simpanKandungan&index='+index+'&kandungan='+kand+'&viewMMKN='+viewMMKN,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f){
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var viewMMKN = $('#viewMMKN').val();
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/syaratTambahan?deleteRow&idKandungan='+idKandungan+'&viewMMKN='+viewMMKN,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }}
    
      
    function addRow(index){
        var viewMMKN = $('#viewMMKN').val();
        var url = '${pageContext.request.contextPath}/pelupusan/syaratTambahan?tambahRow&index='+index+'&viewMMKN='+viewMMKN;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.SyaratTambahanPCRGActionBean" name="form" id="form">
    <s:hidden id="viewMMKN" name="viewMMKN" value="${actionBean.viewForMMKN}"/>
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <br>
            <legend>
                SYARAT TAMBAHAN
            </legend>
            <div class="content" align="center">
                <table>

                    <c:if test="${!actionBean.viewForMMKN}">
                        <c:set var="i" value="1" />
                        <c:set var="k" value="1" />
                        <c:forEach items="${actionBean.senaraiLaporanKandunganPerihalPermohonan}" var="line">

                            <tr>

                                <td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="1.${k}"/></td>
                                <td><s:textarea  id="kandungan1${i}"name="senaraiLaporanKandunganPerihalPermohonan[${i-1}].kandungan" cols="150"  rows="7" class="normal_text"/>
                                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                                </td>

                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="k" value="${k+1}" />
                        </c:forEach>
                        <table>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td  align="left">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('1')"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('1',${i-1})"></s:button>
                                </td>
                            </tr>
                        </table>


                    </c:if>
                    <c:if test="${actionBean.viewForMMKN}">
                        <c:set var="i" value="1" />
                        <c:set var="k" value="1" />
                        <c:forEach items="${actionBean.senaraiLaporanKandunganPerihalPermohonan}" var="line">

                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="1.${k}"/></td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">${line.kandungan}</td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="k" value="${k+1}" />
                        </c:forEach>
                    </c:if>
                </table>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </div>
        </fieldset>
    </div>
</s:form>