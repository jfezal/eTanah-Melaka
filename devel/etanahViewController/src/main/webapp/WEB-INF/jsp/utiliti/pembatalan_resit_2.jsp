
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<%--<script type="text/javascript">
    $(document).ready(function() {

        $("#btn1").show();
        $("#btn2").hide();
    });

    function change(id){
   
        if(id == "LL"){
            if($("#catatan").val() == ""){
                alert('Sila isi ruangan " Catatan ".');
                $("#catatan").focus();
                return true;
            }
        }
   
    }

    function edit(f, id2){
    
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pembatalan_resit?saveN9&"+queryString+"&idKew="+id2, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
 
    }

    function edit1(f, id2){

        var queryString = $(f).formSerialize()
        window.open("${pageContext.request.contextPath}/hasil/pembatalan_resit?saveMlk&"+queryString+"&idKew="+id2, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        
    }

    function edit2(f){
        alert("start edit2");
        var form = $(f).formSerialize();
        var report = 'hasilSuratGantiCekTakLakuResit.rdf';
        alert("report :"+report);
        var param = ${actionBean.dokumenKewangan.idDokumenKewangan};
        alert("param :"+param);
        var param = ${actionBean.dokumenKewangan.senaraiBayaran[0].caraBayaran.idCaraBayaran};

        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+param, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700")
    }

    function edit3(){

        window.open("${pageContext.request.contextPath}/hasil/pembatalan_resit?selectIDHakmilik&", "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
        $("#btn").attr("disabled","true");
    }

    function editPanjangSket(id){
        alert("id :"+id);
    }

    function validate(){

        var kew = $('#sebab').val();
        if(kew == ""){
            alert("Sila Pilih Sebab Tidak Sah.");
            $('#sebab').focus();
            return false;
        }
        return true;
    }

    function cetakBatal(id){
        alert("id :"+id);
        var a = document.getElementById('applet2');
        var count = ${fn:length(actionBean.idKew)};
        var param = "";
        alert("count :"+count);
        for(var i=0;i <= count-1; i++){
            param += ${actionBean.idKew[i]};
            if(i>1 && i<count-1)
                param +=",";
        }
        alert("param :"+param);
        a.printCancelChequeInfo(param);
    }

</script>--%>
<script type="text/javascript">
    $(document).ready(function() {
        $("#btn1").show();
        $("#btn2").hide();
        var catat = "${actionBean.dokumenKewangan.catatan}";
        if(catat == ""){
            $("#catatan").val("TIADA.");
        }
    });

    function cetakSurat(f){
        var form = $(f).formSerialize();
        var report = 'hasilSuratGantiCekTakLakuResit.rdf';
        var param = '${actionBean.idDokumenKewangan}';
        var url = "reportName="+report+"%26report_p_id_kew_dok="+param;
        <%--window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kew_dok="+param, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");--%>
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
            "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
    }

    function validate(){

        var kew = $('#sebab').val();
        if(kew == ""){
            alert("Sila Pilih Sebab Tidak Sah.");
            $('#sebab').focus();
            return false;
        }
        return true;
    }

    function popupPindah(f,idDokKew){

        window.open("${pageContext.request.contextPath}/hasil/pembatalan_resit?selectIDHakmilik&idResit="+idDokKew, "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
        $("#btn").attr("disabled","true");
    }

    function change(id){

        if(id == "LL"){
            if($("#catatan").val() == ""){
                alert('Sila isi ruangan " Catatan ".');
                $("#catatan").focus();
                return true;
            }
        }

    }

    function cetakBatal(id){
        var a = document.getElementById('applet2');
        var ct = "${actionBean.cukaiTanah}";
        var param = "${actionBean.idKewDok}";
        if(param != null || param != ""){
            if(ct == "ya"){
                 a.printCancelChequeInfo(param);
            }else if(ct == "bukan"){
                a.printPerserahanBatal(param);
            }else{
                alert("Terdapat masalah pada parameter.");
            }
        }
    }

    function kesegaranBatalResit(noResit){
        <%--alert("base noResit :"+noResit);--%>
        var q = $('#form1').serialize();
        var url = document.batalResit.action + '?test1&idDokumenKewangan='+noResit;// + event;
        window.location = url+q;
    }

</script>



<div class="subtitle">

    <s:form name="batalResit" beanclass="etanah.view.stripes.hasil.UtilitiPembatalanResitActionBean" id="pembatalan_resit_2">
    
        <table width="100%" bgcolor="green">
            <tr>
                <td width="100%" height="20" >
                    <div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Pembatalan Resit</font>
                    </div>
                </td>
            </tr>
        </table>
        <s:hidden name="idDokumenKewangan"/>
        <s:hidden name="akaun.hakmilik.idHakmilik"/>
        <s:hidden name= "jumlahCaj"/>
        <s:messages/>
        <s:errors/>&nbsp;

        <fieldset class="aras1">
            <legend>Maklumat Kutipan </legend>

            <c:if test="${actionBean.dokumenKewangan.noRujukanManual ne null}">
                <p>
                    <label  for="No Resit">No Resit Kew. 38 :</label>
                    ${actionBean.dokumenKewangan.noRujukanManual}&nbsp;<br>
                </p>
            </c:if>
            <c:if test="${actionBean.dokumenKewangan.noRujukanManual eq null}">
                <p>
                    <label  for="No Resit">No Resit :</label>
                    ${actionBean.idDokumenKewangan}&nbsp;<br>
                </p>
            </c:if>            
            <c:if test="${actionBean.cukaiTanah eq 'ya'}">
                <p>
                    <label  for="Id Hakmilik">ID Hakmilik :</label>
                    ${actionBean.akaun.hakmilik.idHakmilik}&nbsp;<br>
                </p>
            </c:if>
        </fieldset>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Cara Bayaran
                </legend>
                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.dokKewList}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/utiliti/hasil/pembatalan_resit">


                        <display:column title="Bil" sortable="true"><div align="center">${line_rowNum}</div></display:column>
                        <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                            <display:column  title="No Akaun" >
                                ${line.akaun.noAkaun}</display:column>
                        </c:if>
                       <%-- <display:column  title="Jenis Transaksi" >
                            ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}</display:column>

                        <display:column  title="Tahun" >
${line.untukTahun}</display:column>--%>
                        <c:set value="tiada" var="tunai"/>
                        <c:set value="tiada" var="lain"/>
                        <display:column title="Cara Bayaran">
                            <c:forEach items="${line.senaraiBayaran}" var="senarai">
                                <c:if test="${senarai.caraBayaran.kodCaraBayaran.kod eq 'T'}">
                                    <c:set value="ada" var="tunai"/>
                                </c:if>
                                <c:if test="${senarai.caraBayaran.kodCaraBayaran.kod ne 'T'}">
                                    <c:set value="ada" var="lain"/>
                                </c:if>
                                <c:out value="${senarai.caraBayaran.kodCaraBayaran.nama}"/><br>
                            </c:forEach>
                        </display:column>

                        <display:column title="Bank / Agensi Pembayaran">
                            <c:forEach items="${line.senaraiBayaran}" var="senarai">
                                <c:out value="${senarai.caraBayaran.bank.nama}"/><br>
                            </c:forEach>
                        </display:column>
                        <display:column title="No Rujukan">
                            <c:forEach items="${line.senaraiBayaran}" var="senarai">
                                <c:out value="${senarai.caraBayaran.noRujukan}"/><br>
                            </c:forEach>
                        </display:column>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Transaksi" format="{0,date,dd/MM/yyyy,hh:mm aa}" style="text-align:right"/>
                        <display:column property="amaunBayaran" title="Jumlah" format="{0,number, #,###,##0.00}" style="text-align:right"/>
                        <%--         <display:footer>
                                     <tr>
                                         <c:choose>
                                             <c:when test="${actionBean.kodNegeri eq 'melaka'}">
                                                 <td colspan="7" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                             </c:when>
                                             <c:otherwise>
                                                 <td colspan="6" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                             </c:otherwise>
                                         </c:choose>
                                         <td class="number" align="right"><div align="right"><fmt:formatNumber  value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                                     </tr>
                                 </display:footer>--%>
                    </display:table>
                </div>
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Resit Yang Terlibat
                    </legend>
                    <div class="content" align="center">
                        <table>
                            <display:table class="tablecloth" name="${actionBean.listDK}" pagesize="10" cellpadding="0" cellspacing="0" id="line1" requestURI="/utiliti/hasil/pembatalan_resit">

                                <display:column title="Bil" sortable="true"><div align="center">${line1_rowNum}</div></display:column>
                                <display:column  title="No Resit" property="dokumenKewangan.idDokumenKewangan" />
                                <c:if test="${actionBean.cukaiTanah eq 'ya'}">
                                    <display:column  title="ID Hakmilik" property="dokumenKewangan.akaun.hakmilik.idHakmilik" />
                                </c:if>                                
                                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                    <display:column  title="No Akaun" property="dokumenKewangan.akaun.noAkaun" />
                                </c:if>
                                <display:column  title="Amaun (RM)" property="dokumenKewangan.amaunBayaran" format="{0,number, 0.00}" style="text-align:right" />
                            </display:table>
                            <%-- function pindah kt pembatalan resit di mansuh kan sbb digunapakai ditpt lain. arahan kak fida 21032011-->
                            <%--<c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <tr>
                                    <td align="right">
                                        <s:button name="" value="Pindah" class="btn" disabled="${actionBean.btn}" id="btn" onclick="edit3(this.form);" />
                                        <s:button name="" value="Pindah" class="btn" id="btn" onclick="popupPindah(this.form,'${actionBean.dokumenKewangan.idDokumenKewangan}');" disabled="${actionBean.btnPindah}"/>
                                    </td>
                                </tr>
                            </c:if>--%>
                        </table>
                    </div>
                </fieldset>
            </div>
        </c:if>


        <br>


        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Alasan Pembatalan</legend>
                <%--<c:choose>
                    <c:when test="${tunai eq 'ada' and lain eq 'tiada'}">
                    </c:when>
                    <c:otherwise>
                        <div class="instr-fieldset">
                            <font color="red">PERINGATAN: </font>Pilih 'Cek Tendang' dan 'Tarikh Tamat Tempoh' pada ruangan Sebab Tidak Sah akan menyebabkan kesemua transaksi pada kesemua hakmilik terbatal.
                        </div>&nbsp;
                    </c:otherwise>
                </c:choose> --%>
                <p>
                    <label>Sebab Tidak Sah :</label>
                    <s:select name="dokumenKewangan.kodBatal.kod" onblur="validate(this.value)" id ="sebab">
                        <%--<s:option value="">--Sila Pilih--</s:option>--%>
                        <c:choose>
                            <c:when test="${tunai eq 'ada' and lain eq 'tiada'}">
                                <s:options-collection collection="${actionBean.senaraiKodBatalDokumenKewFilter}" label="nama" value="kod"/>
                            </c:when>
                            <c:otherwise>
                                <s:options-collection collection="${listUtil.senaraiKodBatalDokumenKewangan}" label="nama" value="kod"/>
                            </c:otherwise>
                        </c:choose>                       
                    </s:select>
                </p>
                <p>
                    <label for="catatan">Catatan :</label>
                    <s:textarea name="dokumenKewangan.catatan" id="catatan" rows="3" cols="50" onblur="this.value=this.value.toUpperCase();"/>
                    <%--<s:text name="catatan" id="catatan" onblur="this.value=this.value.toUpperCase();"/>--%>
                </p>

                <table border="0" bgcolor="green" width="100%">
                    <s:hidden name="cukaiTanah"/>
                    <tr>
                        <td align="right">

                            <%--<s:submit name="saveN9" value="Simpan" class="btn" onclick="return validate(this.form)" id="next" disabled="${actionBean.btn2}"/>
                            <s:button name=" " value="Isi Semula" class="btn" id="btn3" disabled="${actionBean.btn}" onclick="clearText('pembatalan_resit_2');"/>
                            <s:submit name="test" onclick="edit2(this.form);" value="Cetak Surat" class="btn" disabled="${actionBean.flag1}"/>
                            <c:if test="${actionBean.kodNegeri ne 'melaka'}">
                                <s:button name="cetak_batal" value="Cetak Batal" class="longbtn" onclick="cetakBatal(${actionBean.dokumenKewangan.idDokumenKewangan});" disabled="${actionBean.flag1}"/>
                            </c:if>
                            <s:submit name="kembali" value="Kembali" class="btn" id="btn4"/>--%>
                            <c:if test="${actionBean.cukaiTanah eq 'bukan'}">
                                <s:submit name="savePelbagai" value="Simpan" class="btn" onclick="return validate(this.form)" id="next" disabled="${actionBean.btn2}"/>
                            </c:if>
                            <c:if test="${actionBean.cukaiTanah eq 'ya'}">
                                <s:submit name="saveN9" value="Simpan" class="btn" onclick="return validate(this.form)" id="next" disabled="${actionBean.btn2}"/>
                            </c:if>
                            <s:button name=" " value="Isi Semula" class="btn" id="btn3" disabled="${actionBean.btn}" onclick="clearText('pembatalan_resit_2');"/>
                            <s:submit name="test" onclick="cetakSurat(this.form);" value="Cetak Surat" class="btn" disabled="${actionBean.flag1}"/>
                            <%--<c:if test="${actionBean.kodNegeri ne 'melaka'}">--%>
                                <s:button name="cetak_batal" value="Cetak Batal" class="longbtn" onclick="cetakBatal(${actionBean.idDokumenKewangan});" disabled="${actionBean.flag1}"/>
                            <%--</c:if>--%>
                            <s:submit name="kembali" value="Kembali" class="btn" id="btn4"/>

                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <c:set value="05" var="negeri"/>
         <c:if test="${actionBean.kodNegeri eq 'melaka'}">
            <c:set value="04" var="negeri"/>
        </c:if>
        <applet code="etanah.dokumen.print.PrinterHasil" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
            ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
            ${pageContext.request.contextPath}/commons-logging.jar,
            ${pageContext.request.contextPath}/swingx-1.6.jar,
            ${pageContext.request.contextPath}/log4j-1.2.12.jar,
            ${pageContext.request.contextPath}/jpedal_demo.jar"
                codebase = "${pageContext.request.contextPath}/"
                name     = "applet2"
                id       = "applet2"
                width    = "1"
                height   = "1"
                align    = "middle">
            <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
            <param name="kod_negeri" value="${negeri}"/>
            <param name ="method" value="pdfp">
             <%
            Cookie[] cookies2 = request.getCookies();
            StringBuffer sb2 = new StringBuffer();
            for (int i = 0; i < cookies2.length; i++) {
               sb2.setLength(0);
               sb2.append(cookies2[i].getName());
               sb2.append("=");
               sb2.append(cookies2[i].getValue());
              %>
              <param name="Cookie<%= i %>" value="<%= sb2.toString() %>"><%
            }
            %>
    </applet>

    </s:form>
</div>


