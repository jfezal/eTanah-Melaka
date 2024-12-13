<%--
    Document   : maklumat_tanah_dipohonV2
    Created on : Feb 07, 2013, 03:50 PM
    Author     : Shazwan
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%-- carian hakmilik end--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function() {
        // carian hakmilik start
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),dialogInit('Carian Hakmilik'),$('#catatanKegunaan').hide();
        //carian hakmilik end
        
        /*
         * CUSTOM BY URUSAN   
         *  
         */
        var len = $(".daerah").length;
       
        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/pembangunan/maklumat_hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
            });
        }
    });
    
    function reload (val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?reload&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
        
    function openFrame(type){
        doBlockUI();
        //        alert(idHakmilik);
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?showFormPopUp1&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
        
    function refreshV2MaklumatTanah(){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }

    function refreshV2MaklumatTanah2(){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?refreshpage2';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
    
    function carianHakmilikPopUp(){
        doBlockUI();
        var statusPage = $('#statusPage').val();;
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?carianHakmilikPopup&statusPage="+statusPage, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=980,height=800");
    }
    
    function refreshUntukTutup(){
        doUnBlockUI();
    }
        
    function refreshV2ManyHakmilik(type,hakmilik){
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?refreshpage&type='+type+'&idHakmilik='+hakmilik;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
        
    //Add for charting function
    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }
    
    function viewMohonHakmilik(idKandungan,type)
    {   
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?viewFormPopUp&idKandungan="+idKandungan+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function editMohonHakmilik(idKandungan,type)
    {
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?showFormPopUp&idKandungan="+idKandungan+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    
    function viewForHakmilik(idKandungan,type)
    {   
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?viewFormPopUpForHakmilik&idKandungan="+idKandungan+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function editForHakmilik(idKandungan,type,idHakmilik)
    {
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?showFormPopUpForHakmilik&idKandungan="+idKandungan+"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function deleteMohonHakmilik(idMohonHakmilik,f,tName)
    {   
        NoPrompt();
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?deleteRow&idMohonHakmilik='+idMohonHakmilik+'&tName='+tName,q,
            function(data){
                $('#page_div').html(data);
                    
            }, 'html');
                
            self.refreshpage2('pTerdahulu') ;
        }
    }
    function refreshpage(){
        NoPrompt();
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                var idHakmilik = $('#idHakmilik').val();
                opener.refreshV2ManyHakmilik('main',idHakmilik);
        </c:when>
        <c:otherwise>
                opener.refreshV2('main');
        </c:otherwise>
    </c:choose>
            self.close();
        }
        
        function tutupPapar(){
            //        alert('aa');
            NoPrompt();
        }
        function tambahTanah(type){
    <%--alert(id);--%>
            doBlockUI();
            
            //            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3?showFormPopUp&type="+type, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        }
        
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
<s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>

    <div class="subtitle" id="updiv">
        <c:if test="${actionBean.negeri eq '04'}">
            <fieldset class="aras1">
                <legend>Maklumat Tanah Yang Terlibat</legend>
                <div id ="lotsempadan">
                    <div class="content" align="center">
                        <c:if test="${fn:length(actionBean.senaraiKelompok) ne 0}">
                            <%--<c:forEach items="${actionBean.mohonHakmilikKelompok}" var="line1" begin="1">--%>
                            <display:table class="tablecloth" name="${actionBean.mohonHakmilikKelompok}"
                                           id="line" pagesize="10" style="width:95%"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3">
                                <c:set var="row_num" value="${row_num+1}"/>
                                <c:set var="bcolor" value=""/>
                                <c:set var="title" value="ID Permohonan"/>
                                <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">
                                    ${row_num}
                                </display:column>

                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column property="hakmilik.idHakmilik" style="text-decoration:underline" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                                </c:if>
                                <c:if test="${line.hakmilik.idHakmilik eq null}">
                                    <display:column title="ID Hakmilik">
                                        <center><b>-</b></center>
                                    </display:column>
                                </c:if>
                                <display:column title="No Lot/PT">
                                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                                        ${line.hakmilik.lot.nama} ${line.hakmilik.noLot}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot ne null}">
                                        ${line.lot.nama} ${line.noLot}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot eq null}">
                                        <center><b>-</b></center>
                                    </c:if>
                                </display:column>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                </c:if>
                                <c:if test="${line.hakmilik.idHakmilik eq null}">
                                    <display:column title="Daerah">
                                        ${line.bandarPekanMukimBaru.daerah.nama}
                                    </display:column>
                                </c:if>
                                <display:column title="Bandar/Pekan/Mukim">
                                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                                        ${line.hakmilik.bandarPekanMukim.nama}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                                        ${line.bandarPekanMukimBaru.nama}
                                    </c:if>
                                </display:column>
                                <display:column title="Tempat/Lokasi Tanah Dipohon">
                                    ${line.lokasi}
                                </display:column>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column title="Luas Tanah">
                                        <center>
                                            <s:format value="${line.hakmilik.luas}" formatPattern="#,###,##0.0000"/> ${line.hakmilik.kodUnitLuas.nama}
                                        </center>
                                    </display:column>
                                </c:if>
                                <display:column title="Luas Dipohon">
                                    <center>
                                        <s:format value="${line.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${line.kodUnitLuas.nama}
                                    </center>
                                </display:column>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <display:column title="Kategori Tanah">
                                        ${line.kategoriTanahBaru.nama}
                                    </display:column>
                                    <display:column title="Kegunaan Tanah">
                                        ${line.kodKegunaanTanah.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' || actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                    <display:column title="Kegunaan Tanah Dipohon">
                                        ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                                    <display:column title="Tujuan LPS">
                                        ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                    </display:column>
                                </c:if>
                                <%--<display:column title="Tindakan">
                                    <c:if test="${edit}">
                                        <a onclick="deleteMohonHakmilik(${line.id},this.form,'mohonHakmilik')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                                            <c:if test="${line.hakmilik eq null}">
                                            |<a onclick="editMohonHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                            <c:if test="${line.hakmilik ne null}">
                                            |<a onclick="editForHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${!edit}">
                                            <c:if test="${line.hakmilik eq null}">
                                            <a onclick="viewMohonHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                            <c:if test="${line.hakmilik ne null}">
                                            <a onclick="viewForHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                        </c:if>
                                    </display:column>--%>

                            </display:table>
                            <%--</c:forEach>--%>
                        </c:if>

                        <c:if test="${fn:length(actionBean.senaraiKelompok) eq 0}">
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"
                                           id="line" pagesize="10" style="width:95%"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3">
                                <c:set var="row_num" value="${row_num+1}"/>
                                <c:set var="bcolor" value=""/>
                                <c:set var="title" value="ID Permohonan"/>
                                <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">
                                    ${row_num}
                                </display:column>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column property="hakmilik.idHakmilik" style="text-decoration:underline" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                                </c:if>
                                <c:if test="${line.hakmilik.idHakmilik eq null}">
                                    <display:column title="ID Hakmilik">
                                        <center><b>-</b></center>
                                    </display:column>
                                </c:if>
                                <display:column title="No Lot/PT">
                                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                                        ${line.hakmilik.lot.nama} ${line.hakmilik.noLot}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot ne null}">
                                        ${line.lot.nama} ${line.noLot}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot eq null}">
                                        <center><b>-</b></center>
                                    </c:if>
                                </display:column>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                </c:if>
                                <c:if test="${line.hakmilik.idHakmilik eq null}">
                                    <display:column title="Daerah">
                                        ${line.bandarPekanMukimBaru.daerah.nama}
                                    </display:column>
                                </c:if>

                                <display:column title="Bandar/Pekan/Mukim">
                                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                                        ${line.hakmilik.bandarPekanMukim.nama}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                                        ${line.bandarPekanMukimBaru.nama}
                                    </c:if>
                                </display:column>
                                <display:column title="Tempat/Lokasi Tanah Dipohon">
                                    ${line.lokasi}
                                </display:column>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column title="Luas Tanah">
                                        <center>
                                            <s:format value="${line.hakmilik.luas}" formatPattern="#,###,##0.0000"/> ${line.hakmilik.kodUnitLuas.nama}
                                        </center>
                                    </display:column>
                                </c:if>
                                <display:column title="Luas Dipohon">
                                    <center>
                                        <s:format value="${line.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${line.kodUnitLuas.nama}
                                    </center>
                                </display:column>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <display:column title="Kategori Tanah">
                                        ${line.kategoriTanahBaru.nama}
                                    </display:column>
                                    <display:column title="Kegunaan Tanah">
                                        ${line.kodKegunaanTanah.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' || actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                    <display:column title="Kegunaan Tanah Dipohon">
                                        ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                                    <display:column title="Tujuan LPS">
                                        ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                    </display:column>
                                </c:if>
                                <display:column title="Tindakan">
                                    <c:if test="${edit}">
                                        <a onclick="deleteMohonHakmilik(${line.id},this.form,'mohonHakmilik')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                                            <c:if test="${line.hakmilik eq null}">
                                            |<a onclick="editMohonHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                            <c:if test="${line.hakmilik ne null}">
                                            |<a onclick="editForHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${!edit}">
                                            <c:if test="${line.hakmilik eq null}">
                                            <a onclick="viewMohonHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                            <c:if test="${line.hakmilik ne null}">
                                            <a onclick="viewForHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                            </c:if>
                                        </c:if>
                                    </display:column>
                                </display:table>
                            </c:if>
                            <c:if test="${fn:length(actionBean.senaraiKelompok) eq 0}">
                                <c:if test="${edit}">
                                <span style="float:right">
                                    <s:button class="longbtn" value="Carian Hakmilik" name="idHakmilik" id="idHakmilik" onclick="carianHakmilikPopUp();"/>&nbsp;
                                    <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="tambahTanah('save')"/>
                                </span>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </fieldset>
        </c:if>




        <c:if test="${actionBean.negeri eq '05'}">
            <fieldset class="aras1">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS' && actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                    <legend>Maklumat Tanah Yang Terlibat</legend>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                    <legend>Maklumat Tanah Atas Yang Terlibat</legend>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTS' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC'}">
                    <div id ="lotsempadan">
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"
                                           id="line" pagesize="10" style="width:95%"
                                           requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3">
                                <c:set var="row_num" value="${row_num+1}"/>
                                <c:set var="bcolor" value=""/>
                                <c:set var="title" value="ID Permohonan"/>
                                <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">
                                    ${row_num}
                                </display:column>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column property="hakmilik.idHakmilik" style="text-decoration:underline" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                                </c:if>
                                <c:if test="${line.hakmilik.idHakmilik eq null}">
                                    <display:column title="ID Hakmilik">
                                        <center><b>-</b></center>
                                    </display:column>
                                </c:if>
                                <display:column title="No Lot/PT">
                                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                                        ${line.hakmilik.lot.nama} <fmt:formatNumber pattern="#" value="${line.hakmilik.noLot}"/>
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot ne null}">
                                        ${line.lot.nama} ${line.noLot}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null and line.noLot eq null}">
                                        <center><b>-</b></center>
                                    </c:if>
                                </display:column>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                                </c:if>
                                <c:if test="${line.hakmilik.idHakmilik eq null}">
                                    <display:column title="Daerah">
                                        ${line.bandarPekanMukimBaru.daerah.nama}
                                    </display:column>
                                </c:if>

                                <display:column title="Bandar/Pekan/Mukim">
                                    <c:if test="${line.hakmilik.idHakmilik ne null}">
                                        ${line.hakmilik.bandarPekanMukim.nama}
                                    </c:if>
                                    <c:if test="${line.hakmilik.idHakmilik eq null}">
                                        ${line.bandarPekanMukimBaru.nama}
                                    </c:if>
                                </display:column>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                                    <display:column title="Tempat/Lokasi Tanah Dipohon">
                                        ${line.lokasi}
                                    </display:column>
                                </c:if>
                                <c:if test="${line.hakmilik.idHakmilik ne null}">
                                    <display:column title="Luas Tanah">
                                        <center>
                                            <s:format value="${line.hakmilik.luas}" formatPattern="#,###,##0.0000"/> ${line.hakmilik.kodUnitLuas.nama}
                                        </center>
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                                    <display:column title="Luas Dipohon">
                                        <center>
                                            <s:format value="${line.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${line.kodUnitLuas.nama}
                                        </center>
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                                    <display:column title="Kategori Tanah">
                                        ${line.kategoriTanahBaru.nama}
                                    </display:column>
                                    <display:column title="Kegunaan Tanah">
                                        ${line.kodKegunaanTanah.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                                    <display:column title="Kategori Tanah">
                                        ${line.hakmilik.kategoriTanah.nama}
                                    </display:column>
                                    <display:column title="Kegunaan Tanah">
                                        ${line.hakmilik.kegunaanTanah.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PRMP' || actionBean.permohonan.kodUrusan.kod eq 'LPSP'}">
                                    <display:column title="Kegunaan Tanah Dipohon">
                                        ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                                    <display:column title="Tujuan LPS">
                                        ${actionBean.disPermohonanPermitItem.mohonPermitItem.kodItemPermit.nama}
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                                    <display:column title="Tindakan">
                                        <c:if test="${edit}">
                                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTS' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC'}">
                                                <a onclick="deleteMohonHakmilik(${line.id},this.form,'mohonHakmilik')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a>
                                                </c:if>
                                                <c:if test="${line.hakmilik eq null}">
                                                |<a onclick="editMohonHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                                </c:if>
                                                <c:if test="${line.hakmilik ne null}">
                                                |<a onclick="editForHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${!edit}">
                                                <c:if test="${line.hakmilik eq null}">
                                                <a onclick="viewMohonHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                                </c:if>
                                                <c:if test="${line.hakmilik ne null}">
                                                <a onclick="viewForHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                                </c:if>
                                            </c:if>
                                        </display:column>
                                    </c:if>
                                </display:table>

                            <c:if test="${edit}">
                                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                                    <span style="float:right">
                                        <s:button class="longbtn" value="Carian Hakmilik" name="idHakmilik" id="idHakmilik" onclick="carianHakmilikPopUp();"/>&nbsp;
                                        <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="tambahTanah('save')"/>
                                    </span>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </c:if>
            </fieldset>
        </c:if>


    </div>

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS' || actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
        <div class="subtitle" id="updiv">
            <fieldset class="aras1">
                <legend>Maklumat Tanah Bawah Yang Terlibat</legend>
                <div id ="lotsempadan">
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"
                                       id="line" pagesize="10" style="width:95%"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_tanahV3">
                            <c:set var="row_num" value="${row_num+1}"/>
                            <c:set var="bcolor" value=""/>
                            <c:set var="title" value="ID Permohonan"/>
                            <c:if test="${line.hakmilik.idHakmilik ne null}">
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                            </c:if>
                            <display:column title="No Lot/PT">
                                <c:if test="${line.noLot ne null}">
                                    ${line.lot.nama} ${line.noLot}
                                </c:if>
                                <c:if test="${line.noLot eq null}">
                                    <center><b>-</b></center>
                                </c:if>
                            </display:column>
                            <c:if test="${line.hakmilik.idHakmilik ne null && actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                            </c:if>
                            <display:column title="Daerah">
                                ${line.bandarPekanMukimBaru.daerah.nama}
                            </display:column>

                            <display:column title="Bandar/Pekan/Mukim">
                                <c:if test="${line.bandarPekanMukimBaru eq null}">
                                    <center><b>-</b></center>
                                </c:if>
                                <c:if test="${line.bandarPekanMukimBaru ne null}">
                                    ${line.bandarPekanMukimBaru.nama}
                                </c:if>
                            </display:column>
                            <display:column title="Tempat/Lokasi Tanah Dipohon">
                                ${line.lokasi}
                            </display:column>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                                <display:column title="Pembahagian Tanah">
                                    <c:if test="${line.catatan eq 'SL'}">
                                        Keseluruhan Tanah
                                    </c:if>
                                    <c:if test="${line.catatan eq 'BL'}">
                                        Sebahagian Tanah
                                    </c:if>
                                </display:column>
                            </c:if>
                            <display:column title="Isi Padu Dipohon">
                                <center>
                                    <s:format value="${line.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${line.kodUnitLuas.nama}
                                </center>
                            </display:column>
                            <display:column title="Jarak Kedalaman">
                                <center>
                                    <s:format value="${line.kedalamanTanah}" formatPattern="#,###,##0.0000"/> ${line.kedalamanTanahUOM.nama}
                                </center>
                            </display:column>
                            <display:column title="Kategori Tanah">
                                ${line.kategoriTanahBaru.nama}
                            </display:column>
                            <display:column title="Kegunaan Tanah">
                                ${line.kodKegunaanTanah.nama}
                            </display:column>
                            <display:column title="Tindakan">
                                <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                                    <c:if test="${edit}">
                                        <a onclick="editMohonHakmilik(${line.id},'update')" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                        </c:if>
                                        <c:if test="${!edit}">
                                        <a onclick="viewMohonHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${actionBean.stageId ne '01Kemasukan'}">
                                    <a onclick="viewMohonHakmilik(${line.id},'view')" onmouseover="this.style.cursor='pointer';"><img alt="Papar"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                    </c:if>
                                </display:column>
                            </display:table>

                        <c:if test="${edit}">
                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' && actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS'}">
                                <span style="float:right">
                                    <s:button class="longbtn" value="Carian Hakmilik" name="idHakmilik" id="idHakmilik" onclick="carianHakmilikPopUp();"/>&nbsp;
                                    <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="tambahTanah('save')"/>
                                </span>
                            </c:if>
                        </c:if>
                    </div>

                </div>
            </fieldset>
        </div>
    </c:if>
    <div class="subtitle" id="updiv">
        <c:if test="${actionBean.negeri eq '04'}">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan
                    <c:if test="${fn:length(actionBean.senaraiKelompok) eq 0}">
                        <c:if test="${edit}">
                            <span style="float:right" align="right">
                                <a onclick="openFrame('tujuan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </span>
                        </c:if>
                    </c:if>
                </legend>
                <div id ="lotsempadan">
                    <div class="content" align="center">
                        <c:import url="kemasukanView/kemasukanTujuan.jsp"></c:import>
                    </div>
                </div>
            </fieldset>
        </c:if>

        <c:if test="${actionBean.negeri eq '05'}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTBTC' && actionBean.permohonan.kodUrusan.kod ne 'PTBTS' && actionBean.permohonan.kodUrusan.kod ne 'BMBT'}">
                <fieldset class="aras1">
                    <legend>Maklumat Permohonan
                        <c:if test="${edit}">
                            <span style="float:right" align="right">
                                <a onclick="openFrame('tujuan');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </span>
                        </c:if>
                    </legend>
                    <div id ="lotsempadan">
                        <div class="content" align="center">
                            <c:import url="kemasukanView/kemasukanTujuan.jsp"></c:import>
                        </div>
                    </div>
                </fieldset>
            </c:if>

            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTBTC' || actionBean.permohonan.kodUrusan.kod eq 'PTBTS' || actionBean.permohonan.kodUrusan.kod eq 'BMBT'}">
                <c:if test="${actionBean.stageId ne '01Kemasukan' && actionBean.stageId ne '02ArhnChrtg'}">
                    <fieldset class="aras1">
                        <legend>Tujuan Kegunaan Tanah</legend>
                        <div id ="lotsempadan">
                            <div class="content" align="center">
                                <c:import url="kemasukanView/kemasukanTujuan.jsp"></c:import>
                            </div>
                        </div>
                    </fieldset>
                </c:if>
            </c:if>


        </c:if>


    </div>
</s:form>