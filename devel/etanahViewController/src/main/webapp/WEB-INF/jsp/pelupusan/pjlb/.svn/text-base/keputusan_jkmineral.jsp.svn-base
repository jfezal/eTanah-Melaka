<%-- 
    Document   : keputusan_jkmineral.jsp
    Created on : Oct 3, 2011, 12:05 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function menyimpan(index,i,f){
        var kand;
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
            if(i != 0){
            kand = document.getElementById("kandungan10"+i).value;
            }
        }
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/keputusan_jkmineral?simpan&index='+index+'&kandungan='+kand+'&listSize='+i,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f){
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/keputusan_jkmineral?deleteRow&idKandungan='+idKandungan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }}
    
      
    function addRow(index,f){
        
        var url = '${pageContext.request.contextPath}/pelupusan/keputusan_jkmineral?simpanTambah&index='+index;
        var q = $(f).formSerialize(); 
        $.post(url,q,
        function(data){
            $('#page_div').html(data);
        },'html');
        
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.KeputusanJkMineralActionBean">
    <s:messages/>
    <fieldset class="aras1">
        <legend>Pertimbangan Jawatankuasa Sumber Mineral Negeri</legend>
        <c:if test="${!actionBean.viewOnly}">
            <div align="center">
                <table width="50%" border="0">       
                    <tr>
                        <td colspan="4">&nbsp;</td>             
                    </tr>
                    <tr>   

                        <td>&nbsp;</td>
                        <td align="right"><font color="red" style="text-align: right">*</font>Bil Mesyuarat :</td>
                        <td colspan="2">
                            <s:text name="noSidang" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>


                        <td>&nbsp;</td>
                        <td align="right"><font color="red" style="text-align: right">*</font>Tarikh diterima :</td>
                        <td colspan="2">
                            <s:text name="tarikhTerima" formatPattern="dd/MM/yyyy" size="12" id="datepicker" class="datepicker"/> <em>[hh/bb/tttt]</em>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="right"><font color="red" style="text-align: right">*</font>Tarikh dipertimbangkan :</td>
                        <td colspan="2">
                            <s:text name="tarikhTimbang" formatPattern="dd/MM/yyyy" size="12" id="datepicker2" class="datepicker"/> <em>[hh/bb/tttt]</em>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="right"><font color="red" style="text-align: right">*</font>Kemampuan teknikal dan Kewangan :</td>
                        <td colspan="2">
                            <s:radio name="kemampuan" value="Y"/>&nbsp;Ya
                            <s:radio name="kemampuan" value="T"/>&nbsp;Tidak
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="right"><font color="red" style="text-align: right">*</font>Syor :</td>
                        <td colspan="2">
                            <s:radio name="syortolaklulus" value="L" id="syortolaklulus"/> Diluluskan   
                            <s:radio name="syortolaklulus" value="T" id="syortolaklulus"/> Ditolak
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="right">Ulasan :</td>
                        <td colspan="2">
                            <s:textarea  id="ulasan" name="ulasan" cols="50"  rows="5" class="normal_text"/>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td align="right"><font color="red" style="text-align: right">*</font>Terma & Syarat :</td>
                        <td colspan="2">&nbsp;
                            <!--                    
                            <s:textarea  id="termaDanSyarat" name="termaDanSyarat" cols="50"  rows="5" class="normal_text"/>
                            -->
                        </td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:set var="k" value="1" />
                    <c:forEach items="${actionBean.senaraiLaporanUlas}" var="line">

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td><s:textarea  id="kandungan10${i}"name="senaraiLaporanUlas[${i-1}].ulasan" cols="50"  rows="5" class="normal_text"/></td>
                            <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idLaporUlas})"></s:button><br> </td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="k" value="${k+1}" />
                    </c:forEach>

                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('10',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('10',${i-1},this.form)"></s:button>
                        </td>
                    </tr>

                    <!--              <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td colspan="2">
                    <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </td>
            </tr>-->
                </table>
            </div>
                
        </c:if>
        <c:if test="${actionBean.viewOnly}">
        <div class="content" align="center">
            <table width="50%" border="0">       
                <tr>
                    <td colspan="4">&nbsp;</td> 
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">Bil Mesyuarat :</td>
                    <td colspan="2">
                        ${actionBean.noSidang}
                    </td>   
                   

                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">Tarikh diterima :</td>
                    <td colspan="2">
                        <s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhTerima}"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">Tarikh dipertimbangkan :</td>
                    <td colspan="2">
                        <s:format formatPattern="dd/MM/yyyy" value="${actionBean.tarikhTimbang}"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">Kemampuan teknikal dan Kewangan :</td>
                    <td colspan="2">
                        <c:if test="${actionBean.kemampuan eq 'Y'}">Ya</c:if>
                        <c:if test="${actionBean.kemampuan eq 'T'}">Tidak</c:if>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">Syor :</td>
                    <td colspan="2">
                        <c:if test="${actionBean.syortolaklulus eq 'L'}">Diluluskan</c:if>
                        <c:if test="${actionBean.syortolaklulus eq 'T'}">Ditolak</c:if>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">Ulasan :</td>
                    <td colspan="2">
                        ${actionBean.ulasan}
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">Terma & Syarat yang disyor :</td>
                    <td colspan="2">&nbsp;
                    </td>
                </tr>
                <c:set var="i" value="1" />
                <c:set var="k" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanUlas}" var="line">

                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">${i}) ${line.ulasan}</td>

                    </tr>
                    <c:set var="i" value="${i+1}" />
                    <c:set var="k" value="${k+1}" />
                </c:forEach>
            </table>
        </div>
        </c:if>
    </fieldset>
</s:form>