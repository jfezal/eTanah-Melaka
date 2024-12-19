<%-- 
    Document   : surat_keputusanJkbb
    Created on : Jun 24, 2013, 11:14:02 AM
    Author     : khairul.hazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />--%>

<style type="text/css">
    #tdLabel {
        color:#003194;

        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }

</style>
<script type="text/javascript">
    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;        
        var row = table.insertRow(rowcount);
        <%--var cell0 = row.insertCell(0);
        cell0.innerHTML = (rowcount+1)+") ";--%>
        var cell1 = row.insertCell(0);
        var el = document.createElement('textarea');
        el.name = 'tindakan' + (rowcount+1);
        el.rows = 5;
        el.cols = 125;
        cell1.appendChild(el);
        document.getElementById("rowCount2").value=rowcount+1;
    }

    function deleteRow2()
    {
        var j = document.getElementById('rowCount2').value;
        if(j == 1){
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x= document.getElementById('dataTable2').rows[j-1].cells;
        var idKandungan = x[0].innerHTML;

        document.getElementById('dataTable2').deleteRow(j-1);
        document.getElementById('rowCount2').value= j -1;
        <%--alert(document.getElementById('rowCount').value);--%>                
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/surat_keputusan_jkbb?deleteSingle&idKandungan='
            +idKandungan;
        <%--alert("url:"+url);--%>
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }   
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.SuratKeputusanActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas">${actionBean.kertasK.kertas.idKertas}</s:hidden>
    <s:hidden name="bilangan">${actionBean.bilangan}</s:hidden>
    <s:hidden name="tarikhSidang" formatPattern="dd/MM/yyyy">${actionBean.tarikhSidang}</s:hidden>
    <s:hidden name="keputusanMesyuarat">${actionBean.keputusanMesyuarat}</s:hidden>
    <s:hidden name="pejabat">${actionBean.pejabat}</s:hidden>
    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
               <div class="content" align="center">
                   <table border="0" width="80%" cellspacing="5">                                                            
                        <tr><td>
                                <table border="0" width="100%" cellspacing="10">
                                    <tr>
                                        <td width="15%">Ruj. Fail</td>
                                        <td width="1%">: </td>
                                        <td>${actionBean.idPermohonan} </td>
                                    </tr>
                                    <tr>
                                        <td>Tarikh</td>
                                        <td>: </td>
                                        <td>${actionBean.tarikhPermohonan}</td>
                                    </tr>
                                </table>
                            </td></tr>
                   
                    <tr><td><b>SURAT KEPUTUSAN JKBB </b></td></tr>
                     <tr><td>
                             <table border="0" width="100%" cellspacing="10">
                                    <tr>
                                        <td width="20%">Tajuk</td>
                                        <td>: </td>
                                        <td><s:textarea name="tajuk" rows="5" cols="125" class="normal_text">${actionBean.tajuk}</s:textarea>
                                            
                                        </td>
                                    </tr>
                                    <tr></tr>
                                    <tr>
                                        <td valign="top"></td>
                                        <td valign="top"></td>
                                        <td>Dengan hormatnya saya merujuk kepada perkara di atas dan memaklumkan bahawa 
                                            permohonan tuan telah dibawa untuk pertimbangan Mesyuarat Jawatankuasa Pecah
                                            Sempadan dan Belah Bahagi Negeri Melaka bil. ${actionBean.bilangan} yang bersidang pada
                                            ${actionBean.tarikhSidang} dan keputusannya adalah seperti berikut: </td>
                                    </tr>
                                    <tr></tr>
                                    <tr></tr>
                                    
                                    <tr>
                                        <td width="20%">Keputusan Mesyuarat</td>
                                        <td>: </td>
                                        <td>
                                            <%--<c:if test="${(actionBean.permohonan.kodUrusan.kod ne ('TSKSN'||'TSPSN')  && actionBean.permohonan.keputusan.kod eq 'L')}"> --%>
                                            <c:if test="${(actionBean.permohonan.keputusan.kod eq 'L')}">      
                                            Jawatankuasa mesyuarat <b>${actionBean.keputusanMesyuarat}</b> permohonan tersebut
                                            <s:textarea name="tujuan" rows="5" cols="125" class="normal_text">${actionBean.tujuan}</s:textarea>
                                            </c:if>
                                            
                                            <%--<c:if test="${(actionBean.permohonan.kodUrusan.kod eq ('TSKSN' || 'TSPSN') && actionBean.permohonan.keputusan.kod eq 'L')}">
                                                  
                                            Jawatankuasa mesyuarat <b>${actionBean.keputusanMesyuarat}</b> permohonan tersebut                                           
                                            </c:if> --%>
                                            
                                            <c:if test="${actionBean.permohonan.keputusan.kod eq 'T'}">
                                            Jawatankuasa mesyuarat <b>${actionBean.keputusanMesyuarat}</b> permohonan tersebut
                                            </c:if>
                                        </td>
                                        
                                    </tr>                          
                                    
                                    <c:if test="${(actionBean.permohonan.kodUrusan.kod ne 'TSKKT' && actionBean.permohonan.keputusan.kod eq 'L')}">
                                    <tr></tr>
                                    <tr>
                                        <td width="20%"></td>
                                        <td></td>
                                        <td>Sehubungan dengan itu, pihak tuan diminta untuk mengambil tindakan berikut:-</td>
                                    </tr>
                                    <tr></tr>
                                    <tr>
                                        <td width="20%" valign ="top">Tindakan</td>
                                        <td valign ="top">: </td>
                                        <td><table id ="dataTable2">
                                                   <c:set var="i" value="1" />
                                                   <c:forEach items="${actionBean.senaraiTindakan}" var="line">
                                                        <tr><td style="display:none">${line.idKandungan}</td>                                                           
                                                            <td><s:textarea name="tindakan${i}" id="tindakan${i}"  rows="5" cols="125" class="normal_text">${line.kandungan}</s:textarea></td>
                                                        </tr>
                                                            <c:set var="i" value="${i+1}" />
                                                   </c:forEach>
                                                </table>
                                        </td>
                                    </tr>  
                                    
                                    <tr><td><s:hidden name="rowCount2"  id="rowCount2">${actionBean.rowCount2}</s:hidden> </td> </tr>
                                    <tr>
                                            <td colspan="3">
                                                <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>&nbsp;
                                                <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow2()" />
                                            </td>                
                                    </tr>
                                    </c:if>
                                    
                                    <c:if test="${(actionBean.permohonan.kodUrusan.kod eq 'TSKKT' && actionBean.permohonan.keputusan.kod eq 'L')}">
                                    <tr></tr>
                                    <tr>
                                        <td width="20%"></td>
                                        <td></td>
                                        <td>Sehubungan dengan itu, setelah permohonan ini mendapat kadar nilaian yang ditetapkan, pihak
                                            tuan akan dimaklumkan secara bertulis oleh Pejabat Daerah dan Tanah ${actionBean.pejabat}, Melaka untuk tindakan selanjutnya. </td>
                                    </tr>   
                                    </c:if>
                                </table>
                         </td>
                     </tr>                         
                </table>
                <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>               
                </p>
            </div>
        </fieldset>
    </div>
</s:form>