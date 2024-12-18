<%-- 
    Document   : syor_penolong
    Created on : Jul 6, 2011, 9:55:11 AM
    Author     : faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function deleteRow(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/syorrayuan?deleteRow&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function addRow(index)
    {
        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/syorrayuan?tambahRow&index='+index;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }
    function validate(){
        var syorsokong = document.getElementById("syorsokong");
        var syorsokong2 = document.getElementById("syorsokong2");
        if (syorsokong.checked || syorsokong2.checked)
        {
            return true;
        }else{
            alert('Sila Pilih Disokong atau Ditolak');
            document.getElementById("syorsokong").focus();
            return false;
                
        }

    }
    
</script>
<s:form beanclass="etanah.view.strata.MaklumatSyorActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Syor Penolong Pengarah Strata / Penolong Pegawai Tadbir</legend>

            <p align="center">
            <table  class="tablecloth" align="center" width="70%"  cellpadding="5" cellspacing="0" >
                <tr >
                    <td colspan="3"  valign="middle">            
                        <center> <s:radio name="syor" id="syorsokong" disabled="${readOnly}" value="DI"> </s:radio>&nbsp;Disokong &nbsp;&nbsp;<s:radio disabled="${readOnly}" id="syorsokong2" name="syor" value="DT"> </s:radio>&nbsp;Ditolak</center>

                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <p>
                            ${actionBean.ayatSyor}
                        </p>
                    </td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.listMohonKertasKand}" var="line">


                    <tr>
                        <td width="23" valign="top"><c:out value="3.${i}"/></td>
                        <td valign="top" width="23"><s:hidden name="listMohonKertasKand[${i-1}].bil"/><s:hidden name="listMohonKertasKand[${i-1}].subtajuk"/> <s:textarea  id="kandungan2${i}"name="listMohonKertasKand[${i-1}].kandungan" readonly="${readOnly}" cols="90"  rows="5" class="normal_text"/>&nbsp; </td>
                        <c:if test="${!readOnly}">
                            <td valign="top"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button> </td>
                        </c:if>

                    </tr><c:set var="i" value="${i+1}" />
                </c:forEach>
                <c:if test="${!readOnly}">
                    <tr>
                        <td width="23" valign="top"></td>
                        <td  align="left">

                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> 
                            <s:button name="simpan" id="save1" value="Simpan" class="btn" onclick="if (validate()){doSubmit(this.form,this.name,'page_div');}"></s:button>

                            <%-- <td  align="left">
                                 <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1})"></s:button></td>--%>
                        <td></td>
                    </tr>
                </c:if>
            </table>


        </fieldset>
    </div>

</s:form>

