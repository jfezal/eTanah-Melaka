<%-- 
    Document   : draf_jkbb_mlk
    Created on : Jun 8, 2011, 11:20:39 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>



<script type="text/javascript">
    function save1(event, f){
          
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    
                },'html');
            
        }
    function refreshDrafJKBB(){
        var url = '${pageContext.request.contextPath}/pelupusan/minit_mesyuaratBatuan?refreshDrafJKBB';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function addMinitMesy(){
        window.open("${pageContext.request.contextPath}/pelupusan/minit_mesyuaratBatuan?showTambahMinitMesy", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=710,heighbayaranSyaratt=500,scrollbars=yes");
    }
    function calculateSyarat(){
        var kuantiti = document.getElementById('kuantitiSyarat').value;
        //alert(kuantiti);
        var bayaran = document.getElementById('bayaranSyarat').value;
        //alert(bayaranSyarat);
        var jumlah = kuantiti * bayaran;
        //alert(jumlah);
        var cagaran = 20/100 * jumlah;
        document.getElementById('jumlahSyarat').value = jumlah;
        document.getElementById('cagaranSyarat').value = cagaran;
        document.getElementById('kuantitiJumlahSyarat').value = kuantiti;
    }
    function menyimpan(index,i,f)
    {
        /*
         * LEGEND : 22 -> Perihal Tanah 2.3.*
         */
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
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuaratBatuan?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuaratBatuan?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
             var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuaratBatuan?deleteRow&idKandungan='+idKandungan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
    
</script>
        <s:form beanclass="etanah.view.stripes.pelupusan.MinitMesyuaratBatuan" name="form" id="form">
    <s:messages/>
    <s:errors/>
    <s:hidden name="maxBil" id="maxBil"/>
    <table width="90%" border="0" >
        <tr>
            <td colspan="4">
                    <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <legend> </legend>
                        KERTAS MINIT MESYUARAT
                        <div class="content" align="center">

                            <table border="0" width="80%" cellspacing="10%" align="center">
                                <tr><td id="tdLabel" ><b><font style="text-transform: capitalize">
                                                <tr><td>
                                                        <p><b><span style="text-transform:uppercase"> ${actionBean.tajukMesyuarat}</span></b></p>

                                                    </td></tr></font></b></td></tr>

                                <tr><td>&nbsp;</td></tr>
                            </table>
                        </div>
                    </fieldset>
                </div>
            </td>
          </tr>
          <tr>
              <td colspan="4" id="tblhuraian"><s:textarea name="kenyataanBil1" class="normal_text" rows="5" cols="150"/></td>              
          </tr>
          <c:set var="i" value="1" />
                    <c:set var="num" value="1"/>
                    <c:forEach items="${actionBean.senaraiLaporanKetuaMenteri}" var="line">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                            <td><c:out value="${num}"/></td>
                            <td>
                                    <s:textarea  id="kandungan2${i}"name="senaraiLaporanKetuaMenteri[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                            </td>
                         </tr>
                    <c:set var="i" value="${i+1}" />
                     <c:set var="num" value="${num+1}"/>
                    </c:forEach>
                    <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>
                                <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button> 
                                <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1},this.form)"></s:button>                               
                            </td>
                     </tr>
                     <tr>
                         <td colspan="4" align="center">
                             <s:button name="SimpandrafJKBB" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                         </td>
                     </tr>
                    
    </table>
</s:form>