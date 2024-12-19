<%-- 
    Document   : terimaUlasan_Jabatan_Teknikal
    Created on : Jun 21, 2011, 12:08:45 PM
    Author     : NageswaraRao
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function editJabatanTeknikal(id){   
        window.open("${pageContext.request.contextPath}/pembangunan/melaka/jabatan_teknikal?showEditJabatanTeknikal&idRujukan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=500");
    }

    <%--     function editJabatanTeknikal1(id){
                window.open("${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?showEditJabatanTeknikal1&idRujukan="+id, "eTanah",
             "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
         }
    --%>

        function search(index){
    <%--alert("search:"+index);--%>
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/jabatan_teknikal?kodAgensiPopup&index='+index;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }
        function search1(kod,idRujukan){
            //                alert("Kod Agensi :" + kod);
            //                alert("Id Rujukan :" + idRujukan);
            // alert("search:"+index);
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/jabatan_teknikal?edit&kod='+kod+'&idRujukan='+ idRujukan;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
        }

        function refreshAgensi(){
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/jabatan_teknikal?refreshPage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function removePermohonanRujukanLuar(idRujukan,row){
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/jabatan_teknikal?deleteRujukan&idRujukan='
                    +idRujukan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
        }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.JabatanTeknikalActionBean">
    <div class="subtitle">


        <fieldset class="aras1">
            <br>
            <legend>
                Senarai Jabatan Teknikal  <c:if test="${actionBean.kNegeri eq '04'}">Dan YB Adun</c:if> Yang Terlibat
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiRujukanLuar}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12">
                    <display:column title="Bil." sortable="true">${line_rowNum}</display:column>
                    <%--<display:column title="Nama Jabatan" property="namaAgensi"/>--%>
                    <display:column  title ="Nama Jabatan/ADUN">
                        <u><a onclick="javascript:search1(${line.agensi.kod},${line.idRujukan})">${line.agensi.nama}</a></u>
                    </display:column>
                    <c:if test="${fn:length(line.senaraiSalinan) == 0}">
                        <display:column title="Salinan Kepada">&nbsp;</display:column>
                    </c:if>
                    <c:if test="${line.senaraiSalinan ne ' '}">
                        <c:forEach items="${line.senaraiSalinan}" var="sal">
                            <display:column title="Salinan Kepada">${sal.kodAgensi.nama}</display:column>
                        </c:forEach>
                    </c:if>
                    <display:column title="Tarikh Hantar">
                        <fmt:formatDate value="${line.tarikhDisampai}" pattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Tarikh Jangka Diterima">
                        <fmt:formatDate value="${line.tarikhJangkaTerima}" pattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Tarikh Diterima" >
                        <fmt:formatDate value="${line.tarikhTerima}" pattern="dd/MM/yyyy"/>
                    </display:column>
                    <display:column title="Syor">              

                        <%-- *For Next FAT changes also at Kertas_Pertimbangan_PTG.jsp, ringkasan_permohonan.jsp --%>
                        <c:if test="${line.diSokong eq '1'}">
                            Boleh Diluluskan                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '2'}">
                            Tidak Diluluskan                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '3'}">
                            Tiada Halangan                                            
                        </c:if>
                        <c:if test="${line.diSokong eq '4'}">
                            Sesuai                                          
                        </c:if>
                        <c:if test="${line.diSokong eq '5'}">
                            Tidak Sesuai                                          
                        </c:if>
                        <c:if test="${line.diSokong eq '6'}">
                            Disokong                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '7'}">
                            Tidak Disokong                                          
                        </c:if>
                        <c:if test="${line.diSokong eq '8'}">
                            Tiada Ulasan                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '9'}">
                            Sokong Bersyarat                                       
                        </c:if> 
                        <c:if test="${line.diSokong eq 'A'}">
                            Tangguh                                           
                        </c:if>
                        <c:if test="${line.diSokong eq 'B'}">
                            Tiada Pengesyoran                                     
                        </c:if> 
                        <c:if test="${line.diSokong eq null}">
                            Tiada Ulasan
                        </c:if> 
                        <c:if test="${line.diSokong eq '0'}">
                            Tiada Ulasan
                        </c:if>

                            
                        <%-- 
                        JTEK Ulas    
                        <c:if test="${line.diSokong eq '001'}">
                            Boleh Diluluskan                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '002'}">
                            Tidak Diluluskan                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '003'}">
                            Tiada Halangan                                            
                        </c:if>
                        <c:if test="${line.diSokong eq '004'}">
                            Sesuai                                          
                        </c:if>
                        <c:if test="${line.diSokong eq '005'}">
                            Tidak Sesuai                                          
                        </c:if>
                        <c:if test="${line.diSokong eq '006'}">
                            Disokong                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '007'}">
                            Tidak Disokong                                          
                        </c:if>
                        <c:if test="${line.diSokong eq '008'}">
                            Tiada Ulasan                                           
                        </c:if>
                        <c:if test="${line.diSokong eq '009'}">
                            Sokong Bersyarat                                       
                        </c:if> 
                        <c:if test="${line.diSokong eq null}">
                            Tiada Ulasan
                        </c:if> 
                        --%>
                    </display:column>
                    <display:column title="Status">
                        <c:if test="${line.ulasan ne null}">
                            Lengkap
                        </c:if>
                        <c:if test="${line.ulasan eq null}">
                            <jsp:useBean id="now" class="java.util.Date"/>
                            <c:if test="${line.tarikhJangkaTerima gt now}">
                                Diproses
                            </c:if>
                            <c:if test="${line.tarikhJangkaTerima lt now}">
                                Tiada Ulasan
                            </c:if>
                        </c:if>
                    </display:column>                                 
                    <display:column title="kemaskini">
                        <div align="center">
                            <img alt='Jabatan Teknikal' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                 id='edit_${line_rowNum}' onclick="editJabatanTeknikal('${line.idRujukan}')"  onmouseover="this.style.cursor='pointer';">
                        </div>
                    </display:column>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS'|| actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                        <display:column title ="Hapus">
                            <img alt='Klik Untuk Hapus' border='0' align="middle" src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePermohonanRujukanLuar('${line.idRujukan}','${i}')"  onmouseover="this.style.cursor='pointer';"/>
                        </display:column>
                    </c:if>
                </display:table>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS' || actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                    <s:button name="add" id="tambah" value="Tambah" class="btn" onclick="javascript:search(${i})"/><c:if test="${actionBean.tugasanUtil}"><s:submit name="selesaiTugasan" value="Selesai" class="btn"></s:submit></c:if>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>